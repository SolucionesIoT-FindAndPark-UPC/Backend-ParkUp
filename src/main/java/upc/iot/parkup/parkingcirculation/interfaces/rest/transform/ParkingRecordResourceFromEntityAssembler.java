package upc.iot.parkup.parkingcirculation.interfaces.rest.transform;

import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.ParkingRecordResource;

public class ParkingRecordResourceFromEntityAssembler {
    public static ParkingRecordResource toResourceFromEntity(ParkingRecord entity) {
        return new ParkingRecordResource(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getParkingLotId(),
                entity.getEntryTime(),
                entity.getExitTime(),
                entity.getStatus()
        );
    }
}
