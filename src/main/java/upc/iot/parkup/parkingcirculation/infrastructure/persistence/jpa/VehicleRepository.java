package upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import java.util.Optional;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findAllByUserId(Long userId);
}
