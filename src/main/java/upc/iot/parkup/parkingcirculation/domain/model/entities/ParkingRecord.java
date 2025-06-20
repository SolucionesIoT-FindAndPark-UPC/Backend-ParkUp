package upc.iot.parkup.parkingcirculation.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_records")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private Long parkingLotId;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private LocalDateTime entryTime;

    @Column(nullable = true)
    private LocalDateTime exitTime;

    @Column(nullable = false)
    private String status;
}
