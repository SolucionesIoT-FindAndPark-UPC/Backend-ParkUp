package upc.edu.artcollab.api.camerafeed.interfaces.rest.transform;

import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;
import upc.edu.artcollab.api.camerafeed.interfaces.rest.resources.VideoUploadResponse;

public class VideoUploadResponseFromEntityAssembler {
    public static VideoUploadResponse toResource(VideoRecord entity, String message, boolean success) {
        String videoUrl = entity != null ? entity.getVideoUrl() : "";
        return new VideoUploadResponse(success, message, videoUrl);
    }
}
