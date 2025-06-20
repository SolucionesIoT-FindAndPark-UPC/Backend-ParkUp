package upc.iot.parkup.parkingcirculation.domain.services;

import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleEntryCommand;
import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleExitCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetParkingHistoryByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface CirculationService {
    Optional<ParkingRecord> handle(ProcessVehicleEntryCommand command);
    Optional<ParkingRecord> handle(ProcessVehicleExitCommand command);
    List<ParkingRecord> handle(GetParkingHistoryByUserIdQuery query);
}
