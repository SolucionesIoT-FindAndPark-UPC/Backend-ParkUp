package upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {
    Optional<ParkingRecord> findByVehicleIdAndStatus(Long vehicleId, String status);
    List<ParkingRecord> findByLicensePlateAndStatus(String licensePlate, String status);
    List<ParkingRecord> findByVehicleIdIn(List<Long> vehicleIds);
}
