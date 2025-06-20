package upc.iot.parkup.parkingcirculation.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_records")
@Getter
@Setter
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private Long parkingLotId;

    @Column(nullable = false)
    private String licensePlate; // Denormalized for easier history lookups

    @Column(nullable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Column(nullable = false)
    private String status; // "IN_PROGRESS" or "COMPLETED"

    public ParkingRecord() {}

    public ParkingRecord(Long vehicleId, Long parkingLotId, String licensePlate, LocalDateTime entryTime) {
        this.vehicleId = vehicleId;
        this.parkingLotId = parkingLotId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.status = "IN_PROGRESS";
    }
}
