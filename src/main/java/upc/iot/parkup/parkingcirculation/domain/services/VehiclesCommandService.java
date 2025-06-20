package upc.iot.parkup.parkingcirculation.domain.services;

import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import java.util.Optional;

public interface VehiclesCommandService {
    Optional<Vehicle> handle(RegisterVehicleCommand command);
}
