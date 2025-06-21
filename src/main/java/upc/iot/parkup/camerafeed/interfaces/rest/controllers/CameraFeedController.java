package upc.iot.parkup.camerafeed.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upc.iot.parkup.camerafeed.domain.model.commands.StoreVideoCommand;
import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;
import upc.iot.parkup.camerafeed.domain.model.queries.GetAllVideoRecordsByParkingLotQuery;
import upc.iot.parkup.camerafeed.domain.services.VideoCommandService;
import upc.iot.parkup.camerafeed.domain.services.VideoQueryService;
import upc.iot.parkup.camerafeed.interfaces.rest.resources.VideoRecordResource;
import upc.iot.parkup.camerafeed.interfaces.rest.resources.VideoUploadResponse;
import upc.iot.parkup.camerafeed.interfaces.rest.transform.StoreVideoCommandFromRequestAssembler;
import upc.iot.parkup.camerafeed.interfaces.rest.transform.VideoRecordResourceFromEntityAssembler;
import upc.iot.parkup.camerafeed.interfaces.rest.transform.VideoUploadResponseFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/camera-feed", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Camera Feed", description = "Camera Feed Management Endpoints")
@RequiredArgsConstructor
public class CameraFeedController {

    private final VideoCommandService videoCommandService;
    private final VideoQueryService videoQueryService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoUploadResponse> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("parkingLotId") Long parkingLotId,
            @RequestParam("sourceNodeIdentifier") String sourceNodeIdentifier) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    VideoUploadResponseFromEntityAssembler.toResource(null, "File is empty", false)
            );
        }
        // Basic validation for MP4? Or rely on FileStorageService?
        // For now, let's assume content type check might be good here or in service.
        // if (!"video/mp4".equals(file.getContentType())) {
        // return ResponseEntity.badRequest().body(
        // VideoUploadResponseFromEntityAssembler.toResource(null, "Only MP4 files are allowed", false)
        // );
        // }


        StoreVideoCommand command = StoreVideoCommandFromRequestAssembler.toCommandFromRequest(
                file, parkingLotId, sourceNodeIdentifier
        );

        try {
            Optional<VideoRecord> videoRecordOptional = videoCommandService.handle(command);

            return videoRecordOptional.map(videoRecord ->
                    new ResponseEntity<>(
                            VideoUploadResponseFromEntityAssembler.toResource(videoRecord, "Video uploaded successfully.", true),
                            HttpStatus.CREATED
                    )
            ).orElseGet(() ->
                    new ResponseEntity<>(
                            VideoUploadResponseFromEntityAssembler.toResource(null, "Failed to store video.", false),
                            HttpStatus.INTERNAL_SERVER_ERROR // Or BadRequest if due to input
                    )
            );
        } catch (Exception e) {
            // Log the exception
            return new ResponseEntity<>(
                    VideoUploadResponseFromEntityAssembler.toResource(null, "An unexpected error occurred: " + e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/parking-lot/{parkingLotId}")
    public ResponseEntity<List<VideoRecordResource>> getAllVideoRecordsByParkingLot(
            @PathVariable Long parkingLotId) {

        GetAllVideoRecordsByParkingLotQuery query = new GetAllVideoRecordsByParkingLotQuery(parkingLotId);
        List<VideoRecord> videoRecords = videoQueryService.handle(query);

        if (videoRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<VideoRecordResource> videoRecordResources = videoRecords.stream()
                .map(VideoRecordResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(videoRecordResources);
    }
}
