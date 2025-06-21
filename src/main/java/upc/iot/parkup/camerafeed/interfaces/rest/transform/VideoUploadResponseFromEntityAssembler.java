package upc.iot.parkup.camerafeed.interfaces.rest.transform;

import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;
import upc.iot.parkup.camerafeed.interfaces.rest.resources.VideoUploadResponse;

public class VideoUploadResponseFromEntityAssembler {
    public static VideoUploadResponse toResource(VideoRecord entity, String message, boolean success) {
        String videoUrl = null;
        if (entity != null) {
            videoUrl = entity.getVideoUrl(); // Assuming VideoRecord has getVideoUrl()
        }
        return new VideoUploadResponse(success, message, videoUrl);
    }
}
