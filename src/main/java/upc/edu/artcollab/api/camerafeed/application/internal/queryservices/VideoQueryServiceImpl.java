package upc.edu.artcollab.api.camerafeed.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;
import upc.edu.artcollab.api.camerafeed.domain.model.queries.GetAllVideoRecordsByParkingLotQuery;
import upc.edu.artcollab.api.camerafeed.domain.model.queries.GetVideoRecordByIdQuery;
import upc.edu.artcollab.api.camerafeed.domain.services.VideoQueryService;
import upc.edu.artcollab.api.camerafeed.infrastructure.persistence.jpa.VideoRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideoQueryServiceImpl implements VideoQueryService {

    private final VideoRecordRepository videoRecordRepository;

    public VideoQueryServiceImpl(VideoRecordRepository videoRecordRepository) {
        this.videoRecordRepository = videoRecordRepository;
    }

    @Override
    public Optional<VideoRecord> handle(GetVideoRecordByIdQuery query) {
        return videoRecordRepository.findById(query.videoId());
    }

    @Override
    public List<VideoRecord> handle(GetAllVideoRecordsByParkingLotQuery query) {
        return videoRecordRepository.findAllByParkingLotId(query.parkingLotId());
    }
}
