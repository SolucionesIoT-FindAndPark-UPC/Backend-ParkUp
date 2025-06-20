package upc.iot.parkup.parkingcirculation.application.internal.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleEntryCommand;
import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleExitCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetParkingHistoryByUserIdQuery;
import upc.iot.parkup.parkingcirculation.domain.services.CirculationService;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.ParkingRecordRepository;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehicleByLicensePlateQuery;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CirculationServiceImpl implements CirculationService {

    private static final Logger log = LoggerFactory.getLogger(CirculationServiceImpl.class);

    private final ParkingRecordRepository parkingRecordRepository;
    private final VehiclesService vehiclesService; // To get vehicle details

    public CirculationServiceImpl(ParkingRecordRepository parkingRecordRepository, VehiclesService vehiclesService) {
        this.parkingRecordRepository = parkingRecordRepository;
        this.vehiclesService = vehiclesService;
    }

    @Override
    public Optional<ParkingRecord> handle(ProcessVehicleEntryCommand command) {
        log.info("Handling ProcessVehicleEntryCommand: {}", command);
        Optional<Vehicle> vehicleOptional = vehiclesService.handle(new GetVehicleByLicensePlateQuery(command.licensePlate()));

        if (vehicleOptional.isEmpty()) {
            log.warn("Vehicle with license plate {} not found.", command.licensePlate());
            throw new IllegalArgumentException("Vehicle with license plate " + command.licensePlate() + " not found.");
        }

        Vehicle vehicle = vehicleOptional.get();

        // Check if vehicle is already in a parking lot (status IN_PROGRESS)
        List<ParkingRecord> existingRecords = parkingRecordRepository.findByLicensePlateAndStatus(command.licensePlate(), "IN_PROGRESS");
        if (!existingRecords.isEmpty()) {
            log.warn("Vehicle {} is already marked as IN_PROGRESS in a parking lot.", command.licensePlate());
            throw new IllegalStateException("Vehicle " + command.licensePlate() + " is already in a parking lot.");
        }

        var parkingRecord = new ParkingRecord(vehicle.getId(), command.parkingLotId(), vehicle.getLicensePlate(), LocalDateTime.now());
        try {
            parkingRecordRepository.save(parkingRecord);
            log.info("Vehicle entry processed successfully for license plate {}: {}", command.licensePlate(), parkingRecord.getId());
            return Optional.of(parkingRecord);
        } catch (Exception e) {
            log.error("Error processing vehicle entry for license plate {}: {}", command.licensePlate(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ParkingRecord> handle(ProcessVehicleExitCommand command) {
        log.info("Handling ProcessVehicleExitCommand: {}", command);
        List<ParkingRecord> parkingRecordsInProgress = parkingRecordRepository.findByLicensePlateAndStatus(command.licensePlate(), "IN_PROGRESS");

        if (parkingRecordsInProgress.isEmpty()) {
            log.warn("No IN_PROGRESS parking record found for license plate {} and parking lot ID {}", command.licensePlate(), command.parkingLotId());
            throw new IllegalArgumentException("No active parking record found for vehicle " + command.licensePlate() + " in parking lot " + command.parkingLotId());
        }
        // Assuming a vehicle can only be in one parking lot at a time, so take the first one.
        // Or, if multiple parking lots are possible, the command should specify which parkingLotId to exit from.
        // The current command has parkingLotId, so we should filter by it.
        Optional<ParkingRecord> recordToUpdateOptional = parkingRecordsInProgress.stream()
            .filter(pr -> pr.getParkingLotId().equals(command.parkingLotId()))
            .findFirst();

        if (recordToUpdateOptional.isEmpty()) {
            log.warn("No IN_PROGRESS parking record found for license plate {} and specific parking lot ID {}", command.licensePlate(), command.parkingLotId());
            throw new IllegalArgumentException("No active parking record found for vehicle " + command.licensePlate() + " in parking lot " + command.parkingLotId());
        }

        ParkingRecord parkingRecord = recordToUpdateOptional.get();
        parkingRecord.setExitTime(LocalDateTime.now());
        parkingRecord.setStatus("COMPLETED");
        try {
            parkingRecordRepository.save(parkingRecord);
            log.info("Vehicle exit processed successfully for license plate {}: {}", command.licensePlate(), parkingRecord.getId());
            return Optional.of(parkingRecord);
        } catch (Exception e) {
            log.error("Error processing vehicle exit for license plate {}: {}", command.licensePlate(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<ParkingRecord> handle(GetParkingHistoryByUserIdQuery query) {
        log.info("Handling GetParkingHistoryByUserIdQuery for user ID: {}", query.userId());
        List<Vehicle> userVehicles = vehiclesService.handle(new upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehiclesByUserIdQuery(query.userId()));

        if (userVehicles.isEmpty()) {
            log.info("No vehicles found for user ID: {}", query.userId());
            return Collections.emptyList();
        }

        List<Long> vehicleIds = userVehicles.stream().map(Vehicle::getId).collect(Collectors.toList());
        return parkingRecordRepository.findByVehicleIdIn(vehicleIds);
    }
}
