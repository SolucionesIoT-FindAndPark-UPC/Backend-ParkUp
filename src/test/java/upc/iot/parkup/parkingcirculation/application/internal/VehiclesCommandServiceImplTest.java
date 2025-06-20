package upc.iot.parkup.parkingcirculation.application.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import upc.iot.parkup.parkingcirculation.application.acl.IamContextFacadeForParkingCirculation;
import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.VehicleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehiclesCommandServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private IamContextFacadeForParkingCirculation iamContextFacade;

    @InjectMocks
    private VehiclesCommandServiceImpl vehiclesCommandService;

    private RegisterVehicleCommand registerVehicleCommand;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        registerVehicleCommand = new RegisterVehicleCommand("ABC-123", 1L);
        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setLicensePlate("ABC-123");
        vehicle.setUserId(1L);
    }

    @Test
    void testHandleRegisterVehicleCommand_Success() {
        // Given
        when(vehicleRepository.findByLicensePlate(registerVehicleCommand.licensePlate())).thenReturn(Optional.empty());
        when(iamContextFacade.doesUserExistById(registerVehicleCommand.userId())).thenReturn(true);
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        // When
        Optional<Vehicle> result = vehiclesCommandService.handle(registerVehicleCommand);

        // Then
        assertTrue(result.isPresent());
        assertEquals(vehicle.getLicensePlate(), result.get().getLicensePlate());
        assertEquals(vehicle.getUserId(), result.get().getUserId());
        verify(vehicleRepository).save(any(Vehicle.class));
    }

    @Test
    void testHandleRegisterVehicleCommand_VehicleAlreadyExists() {
        // Given
        when(vehicleRepository.findByLicensePlate(registerVehicleCommand.licensePlate())).thenReturn(Optional.of(new Vehicle()));

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            vehiclesCommandService.handle(registerVehicleCommand);
        });
        assertEquals("Vehicle with license plate " + registerVehicleCommand.licensePlate() + " already exists", exception.getMessage());
    }

    @Test
    void testHandleRegisterVehicleCommand_UserNotFound() {
        // Given
        when(vehicleRepository.findByLicensePlate(registerVehicleCommand.licensePlate())).thenReturn(Optional.empty());
        when(iamContextFacade.doesUserExistById(registerVehicleCommand.userId())).thenReturn(false);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            vehiclesCommandService.handle(registerVehicleCommand);
        });
        assertEquals("User with id " + registerVehicleCommand.userId() + " not found", exception.getMessage());
    }
}
