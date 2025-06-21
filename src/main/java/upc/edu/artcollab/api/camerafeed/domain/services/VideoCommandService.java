package upc.edu.artcollab.api.camerafeed.domain.services;

import upc.edu.artcollab.api.camerafeed.domain.model.commands.StoreVideoCommand;
import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;

import java.util.Optional;

public interface VideoCommandService {
    Optional<VideoRecord> handle(StoreVideoCommand command);
}
