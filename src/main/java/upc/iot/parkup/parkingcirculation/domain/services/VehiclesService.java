package upc.iot.parkup.parkingcirculation.domain.services;

import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehicleByLicensePlateQuery;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehiclesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehiclesService {
    Optional<Vehicle> handle(RegisterVehicleCommand command);
    Optional<Vehicle> handle(GetVehicleByLicensePlateQuery query);
    List<Vehicle> handle(GetVehiclesByUserIdQuery query);
}
