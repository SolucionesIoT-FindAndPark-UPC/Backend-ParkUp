package upc.edu.artcollab.api.camerafeed.interfaces.rest.transform;

import org.springframework.web.multipart.MultipartFile;
import upc.edu.artcollab.api.camerafeed.domain.model.commands.StoreVideoCommand;

public class StoreVideoCommandFromRequestAssembler {
    public static StoreVideoCommand toCommandFromRequest(
            MultipartFile file,
            Long parkingLotId,
            String sourceNodeIdentifier
    ) {
        return new StoreVideoCommand(file, parkingLotId, sourceNodeIdentifier);
    }
}
