package upc.edu.artcollab.api.camerafeed.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record StoreVideoCommand(
        MultipartFile file,
        Long parkingLotId,
        String sourceNodeIdentifier
) {
}
