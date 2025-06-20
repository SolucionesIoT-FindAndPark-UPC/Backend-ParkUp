package upc.iot.parkup.parkingcirculation.application.internal;

import org.springframework.stereotype.Service;
import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleEntryCommand;
import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleExitCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.services.CirculationCommandService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.ParkingRecordRepository;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.VehicleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CirculationCommandServiceImpl implements CirculationCommandService {

    private final ParkingRecordRepository parkingRecordRepository;
    private final VehicleRepository vehicleRepository;

    public CirculationCommandServiceImpl(ParkingRecordRepository parkingRecordRepository, VehicleRepository vehicleRepository) {
        this.parkingRecordRepository = parkingRecordRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<ParkingRecord> handle(ProcessVehicleEntryCommand command) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(command.licensePlate())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with license plate " + command.licensePlate() + " not found"));

        Long vehicleId = vehicle.getId();

        if (parkingRecordRepository.findByVehicleIdAndStatus(vehicleId, "IN_PROGRESS").isPresent()) {
            throw new IllegalStateException("Vehicle " + command.licensePlate() + " is already parked.");
        }

        ParkingRecord parkingRecord = ParkingRecord.builder()
                .vehicleId(vehicleId)
                .parkingLotId(command.parkingLotId())
                .licensePlate(command.licensePlate())
                .entryTime(LocalDateTime.now())
                .status("IN_PROGRESS")
                .build();

        var createdRecord = parkingRecordRepository.save(parkingRecord);
        return Optional.of(createdRecord);
    }

    @Override
    public Optional<ParkingRecord> handle(ProcessVehicleExitCommand command) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(command.licensePlate())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with license plate " + command.licensePlate() + " not found"));

        Long vehicleId = vehicle.getId();

        // Find the "IN_PROGRESS" record. The command's parkingLotId might be used to ensure exiting from the correct lot
        // if a vehicle could somehow be in multiple lots (not typical).
        // For now, we find any "IN_PROGRESS" record for the vehicle.
        // If a vehicle can only be in one lot at a time, parkingLotId in ExitCommand might be redundant for finding the record,
        // but useful for validation if the system supports multiple lots.
        // The current findByVehicleIdAndStatus in repo is fine.
        ParkingRecord parkingRecord = parkingRecordRepository.findByVehicleIdAndStatus(vehicleId, "IN_PROGRESS")
                .orElseThrow(() -> new IllegalStateException("Vehicle " + command.licensePlate() + " is not currently parked or no 'IN_PROGRESS' record found."));

        // Optional: Validate if parkingRecord.getParkingLotId() matches command.parkingLotId() if that's a requirement.
        // if (!parkingRecord.getParkingLotId().equals(command.parkingLotId())) {
        //     throw new IllegalStateException("Vehicle is parked in a different parking lot.");
        // }

        parkingRecord.setExitTime(LocalDateTime.now());
        parkingRecord.setStatus("COMPLETED");

        var updatedRecord = parkingRecordRepository.save(parkingRecord);
        return Optional.of(updatedRecord);
    }
}
