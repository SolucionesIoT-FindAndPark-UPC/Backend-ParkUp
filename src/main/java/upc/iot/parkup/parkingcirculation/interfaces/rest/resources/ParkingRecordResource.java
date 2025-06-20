package upc.iot.parkup.parkingcirculation.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ParkingRecordResource(
        Long id,
        String licensePlate,
        Long parkingLotId,
        LocalDateTime entryTime,
        LocalDateTime exitTime,
        String status) {
}
