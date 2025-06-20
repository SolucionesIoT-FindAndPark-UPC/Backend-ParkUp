package upc.iot.parkup.parkingcirculation.application.internal;

import org.springframework.stereotype.Service;
import upc.iot.parkup.parkingcirculation.application.acl.IamContextFacadeForParkingCirculation;
import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesCommandService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.VehicleRepository;

import java.util.Optional;

@Service
public class VehiclesCommandServiceImpl implements VehiclesCommandService {

    private final VehicleRepository vehicleRepository;
    private final IamContextFacadeForParkingCirculation iamContextFacade;

    public VehiclesCommandServiceImpl(VehicleRepository vehicleRepository, IamContextFacadeForParkingCirculation iamContextFacade) {
        this.vehicleRepository = vehicleRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    public Optional<Vehicle> handle(RegisterVehicleCommand command) {
        if (vehicleRepository.findByLicensePlate(command.licensePlate()).isPresent()) {
            throw new IllegalArgumentException("Vehicle with license plate " + command.licensePlate() + " already exists");
        }

        if (!iamContextFacade.doesUserExistById(command.userId())) {
            throw new IllegalArgumentException("User with id " + command.userId() + " not found");
        }

        var vehicle = new Vehicle();
        vehicle.setLicensePlate(command.licensePlate());
        vehicle.setUserId(command.userId());
        // Assuming Vehicle entity has appropriate setters or a builder
        // vehicle = Vehicle.builder().licensePlate(command.licensePlate()).userId(command.userId()).build();


        var createdVehicle = vehicleRepository.save(vehicle);
        return Optional.of(createdVehicle);
    }
}
