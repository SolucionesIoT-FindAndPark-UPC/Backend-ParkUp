package upc.iot.parkup.camerafeed.interfaces.rest.resources;

import java.time.LocalDateTime;

public record VideoRecordResource(
        Long id,
        String videoUrl,
        Long parkingLotId,
        LocalDateTime timestamp
) {
}
