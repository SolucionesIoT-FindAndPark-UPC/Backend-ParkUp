package upc.iot.parkup.parkingcirculation.application.internal.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import upc.iot.parkup.parkingcirculation.application.acl.IamContextFacadeForParkingCirculation;
import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehicleByLicensePlateQuery;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehiclesByUserIdQuery;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesServiceImpl implements VehiclesService {

    private static final Logger log = LoggerFactory.getLogger(VehiclesServiceImpl.class);

    private final VehicleRepository vehicleRepository;
    private final IamContextFacadeForParkingCirculation iamContextFacade;

    public VehiclesServiceImpl(VehicleRepository vehicleRepository, IamContextFacadeForParkingCirculation iamContextFacade) {
        this.vehicleRepository = vehicleRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    public Optional<Vehicle> handle(RegisterVehicleCommand command) {
        log.info("Handling RegisterVehicleCommand: {}", command);
        if (!iamContextFacade.existsUserById(command.userId())) {
            log.warn("User with ID {} does not exist in IAM context.", command.userId());
            throw new IllegalArgumentException("User with ID " + command.userId() + " does not exist.");
        }
        if (vehicleRepository.existsByLicensePlate(command.licensePlate())) {
            log.warn("Vehicle with license plate {} already exists.", command.licensePlate());
            throw new IllegalArgumentException("Vehicle with license plate " + command.licensePlate() + " already exists.");
        }
        var vehicle = new Vehicle(command.licensePlate(), command.userId());
        try {
            vehicleRepository.save(vehicle);
            log.info("Vehicle registered successfully: {}", vehicle.getId());
            return Optional.of(vehicle);
        } catch (Exception e) {
            log.error("Error registering vehicle: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByLicensePlateQuery query) {
        log.info("Handling GetVehicleByLicensePlateQuery: {}", query);
        return vehicleRepository.findByLicensePlate(query.licensePlate());
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByUserIdQuery query) {
        log.info("Handling GetVehiclesByUserIdQuery: {}", query);
        return vehicleRepository.findByUserId(query.userId());
    }
}
