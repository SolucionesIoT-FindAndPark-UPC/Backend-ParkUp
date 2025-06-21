package upc.edu.artcollab.api.camerafeed.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;

import java.util.List;

@Repository
public interface VideoRecordRepository extends JpaRepository<VideoRecord, Long> {
    List<VideoRecord> findAllByParkingLotId(Long parkingLotId);
}
