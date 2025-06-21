package upc.edu.artcollab.api.camerafeed.application.internal.commandservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.camerafeed.application.internal.outboundservices.storage.FileStorageService;
import upc.edu.artcollab.api.camerafeed.domain.model.commands.StoreVideoCommand;
import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;
import upc.edu.artcollab.api.camerafeed.domain.services.VideoCommandService;
import upc.edu.artcollab.api.camerafeed.infrastructure.persistence.jpa.VideoRecordRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class VideoCommandServiceImpl implements VideoCommandService {

    private final VideoRecordRepository videoRecordRepository;
    private final FileStorageService fileStorageService;

    public VideoCommandServiceImpl(VideoRecordRepository videoRecordRepository, FileStorageService fileStorageService) {
        this.videoRecordRepository = videoRecordRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Optional<VideoRecord> handle(StoreVideoCommand command) {
        try {
            String fileUrl = fileStorageService.save(command.file());
            VideoRecord videoRecord = new VideoRecord(
                    fileUrl,
                    command.parkingLotId(),
                    command.sourceNodeIdentifier(),
                    LocalDateTime.now()
            );
            VideoRecord savedRecord = videoRecordRepository.save(videoRecord);
            return Optional.of(savedRecord);
        } catch (Exception e) {
            log.error("Error while storing video: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}
