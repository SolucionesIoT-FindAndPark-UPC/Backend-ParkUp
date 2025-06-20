package upc.iot.parkup.parkingcirculation.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "licensePlate")
})
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Long userId;

    public Vehicle() {}

    public Vehicle(String licensePlate, Long userId) {
        this.licensePlate = licensePlate;
        this.userId = userId;
    }
}
