package upc.edu.artcollab.api.camerafeed.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upc.edu.artcollab.api.camerafeed.domain.model.commands.StoreVideoCommand;
import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;
import upc.edu.artcollab.api.camerafeed.domain.model.queries.GetAllVideoRecordsByParkingLotQuery;
import upc.edu.artcollab.api.camerafeed.domain.services.VideoCommandService;
import upc.edu.artcollab.api.camerafeed.domain.services.VideoQueryService;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.resources.VideoRecordResource;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.resources.VideoUploadResponse;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.transform.StoreVideoCommandFromRequestAssembler;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.transform.VideoRecordResourceFromEntityAssembler;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.transform.VideoUploadResponseFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/camera-feed", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Camera Feed", description = "Camera Feed Management Endpoints")
@Slf4j
public class CameraFeedController {

    private final VideoCommandService videoCommandService;
    private final VideoQueryService videoQueryService;

    public CameraFeedController(VideoCommandService videoCommandService, VideoQueryService videoQueryService) {
        this.videoCommandService = videoCommandService;
        this.videoQueryService = videoQueryService;
    }

    @PostMapping("/upload")
    public ResponseEntity<VideoUploadResponse> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("parkingLotId") Long parkingLotId,
            @RequestParam("sourceNodeIdentifier") String sourceNodeIdentifier) {
        try {
            StoreVideoCommand command = StoreVideoCommandFromRequestAssembler.toCommandFromRequest(file, parkingLotId, sourceNodeIdentifier);
            Optional<VideoRecord> videoRecordOptional = videoCommandService.handle(command);

            return videoRecordOptional.map(videoRecord -> {
                VideoUploadResponse response = VideoUploadResponseFromEntityAssembler.toResource(videoRecord, "Video uploaded successfully.", true);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }).orElseGet(() -> {
                VideoUploadResponse response = VideoUploadResponseFromEntityAssembler.toResource(null, "Failed to upload video.", false);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            });
        } catch (Exception e) {
            log.error("Error during video upload: {}", e.getMessage(), e);
            VideoUploadResponse response = VideoUploadResponseFromEntityAssembler.toResource(null, "An unexpected error occurred: " + e.getMessage(), false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/parking-lot/{parkingLotId}")
    public ResponseEntity<List<VideoRecordResource>> getAllVideoRecordsByParkingLot(@PathVariable Long parkingLotId) {
        try {
            GetAllVideoRecordsByParkingLotQuery query = new GetAllVideoRecordsByParkingLotQuery(parkingLotId);
            List<VideoRecord> videoRecords = videoQueryService.handle(query);
            if (videoRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<VideoRecordResource> resources = videoRecords.stream()
                    .map(VideoRecordResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(resources);
        } catch (Exception e) {
            log.error("Error retrieving video records for parking lot {}: {}", parkingLotId, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
