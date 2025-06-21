package upc.iot.parkup.camerafeed.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "video_records")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private Long parkingLotId;

    @Column(nullable = false)
    private String sourceNodeIdentifier;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    public VideoRecord(String videoUrl, Long parkingLotId, String sourceNodeIdentifier) {
        this.videoUrl = videoUrl;
        this.parkingLotId = parkingLotId;
        this.sourceNodeIdentifier = sourceNodeIdentifier;
    }
}
