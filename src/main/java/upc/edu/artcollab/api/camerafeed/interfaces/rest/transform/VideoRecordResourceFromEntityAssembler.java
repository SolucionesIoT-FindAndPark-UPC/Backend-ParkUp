package upc.edu.artcollab.api.camerafeed.interfaces.rest.transform;

import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.resources.VideoRecordResource;

public class VideoRecordResourceFromEntityAssembler {
    public static VideoRecordResource toResourceFromEntity(VideoRecord entity) {
        return new VideoRecordResource(
                entity.getId(),
                entity.getVideoUrl(),
                entity.getParkingLotId(),
                entity.getTimestamp()
        );
    }
}
