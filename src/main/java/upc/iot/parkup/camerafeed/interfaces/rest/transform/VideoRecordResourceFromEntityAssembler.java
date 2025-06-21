package upc.iot.parkup.camerafeed.interfaces.rest.transform;

import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;
import upc.iot.parkup.camerafeed.interfaces.rest.resources.VideoRecordResource;

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
