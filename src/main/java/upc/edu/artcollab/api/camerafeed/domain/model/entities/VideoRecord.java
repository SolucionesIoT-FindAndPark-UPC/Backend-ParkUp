package upc.edu.artcollab.api.camerafeed.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "video_records")
@Getter
@Setter
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

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public VideoRecord() {
    }

    public VideoRecord(String videoUrl, Long parkingLotId, String sourceNodeIdentifier, LocalDateTime timestamp) {
        this.videoUrl = videoUrl;
        this.parkingLotId = parkingLotId;
        this.sourceNodeIdentifier = sourceNodeIdentifier;
        this.timestamp = timestamp;
    }
}
