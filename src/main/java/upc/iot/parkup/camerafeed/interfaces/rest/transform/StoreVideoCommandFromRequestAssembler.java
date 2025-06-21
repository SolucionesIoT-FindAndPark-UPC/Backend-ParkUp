package upc.iot.parkup.camerafeed.interfaces.rest.transform;

import org.springframework.web.multipart.MultipartFile;
import upc.iot.parkup.camerafeed.domain.model.commands.StoreVideoCommand;

public class StoreVideoCommandFromRequestAssembler {
    public static StoreVideoCommand toCommandFromRequest(
            MultipartFile file,
            Long parkingLotId,
            String sourceNodeIdentifier
    ) {
        return new StoreVideoCommand(file, parkingLotId, sourceNodeIdentifier);
    }
}
