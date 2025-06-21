package upc.iot.parkup.camerafeed.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.iot.parkup.camerafeed.application.internal.outboundservices.storage.FileStorageService;
import upc.iot.parkup.camerafeed.domain.model.commands.StoreVideoCommand;
import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;
import upc.iot.parkup.camerafeed.domain.services.VideoCommandService;
import upc.iot.parkup.camerafeed.infrastructure.persistence.jpa.VideoRecordRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoCommandServiceImpl implements VideoCommandService {

    private final VideoRecordRepository videoRecordRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    @Override
    public Optional<VideoRecord> handle(StoreVideoCommand command) {
        String videoUrl = fileStorageService.save(command.file());
        if (videoUrl == null || videoUrl.isEmpty()) {
            // Optionally, log an error or throw a more specific exception
            return Optional.empty();
        }

        VideoRecord videoRecord = new VideoRecord(
                videoUrl,
                command.parkingLotId(),
                command.sourceNodeIdentifier()
        );

        try {
            VideoRecord savedRecord = videoRecordRepository.save(videoRecord);
            return Optional.of(savedRecord);
        } catch (Exception e) {
            // Optionally, log the exception
            // Consider if the saved file should be deleted in case of DB error
            return Optional.empty();
        }
    }
}
