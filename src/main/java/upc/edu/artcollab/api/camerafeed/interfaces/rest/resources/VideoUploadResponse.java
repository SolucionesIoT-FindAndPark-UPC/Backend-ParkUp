package upc.edu.artcollab.api.camerafeed.interfaces.rest.resources;

public record VideoUploadResponse(
        boolean success,
        String message,
        String videoUrl
) {
}
