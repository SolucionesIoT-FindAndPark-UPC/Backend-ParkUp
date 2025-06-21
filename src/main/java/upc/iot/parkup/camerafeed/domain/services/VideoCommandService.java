package upc.iot.parkup.camerafeed.domain.services;

import upc.iot.parkup.camerafeed.domain.model.commands.StoreVideoCommand;
import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;

import java.util.Optional;

public interface VideoCommandService {
    Optional<VideoRecord> handle(StoreVideoCommand command);
}
