package upc.iot.parkup.parkingcirculation.domain.services;

import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleEntryCommand;
import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleExitCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import java.util.Optional;

public interface CirculationCommandService {
    Optional<ParkingRecord> handle(ProcessVehicleEntryCommand command);
    Optional<ParkingRecord> handle(ProcessVehicleExitCommand command);
}
