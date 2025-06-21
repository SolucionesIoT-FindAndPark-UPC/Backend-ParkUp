package upc.iot.parkup.camerafeed.interfaces.rest.resources;

public record VideoUploadResponse(
        boolean success,
        String message,
        String videoUrl
) {
}
