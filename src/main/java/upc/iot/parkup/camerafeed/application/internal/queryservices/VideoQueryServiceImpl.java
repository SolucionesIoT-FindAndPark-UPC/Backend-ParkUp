package upc.iot.parkup.camerafeed.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;
import upc.iot.parkup.camerafeed.domain.model.queries.GetAllVideoRecordsByParkingLotQuery;
import upc.iot.parkup.camerafeed.domain.model.queries.GetVideoRecordByIdQuery;
import upc.iot.parkup.camerafeed.domain.services.VideoQueryService;
import upc.iot.parkup.camerafeed.infrastructure.persistence.jpa.VideoRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoQueryServiceImpl implements VideoQueryService {

    private final VideoRecordRepository videoRecordRepository;

    @Override
    public Optional<VideoRecord> handle(GetVideoRecordByIdQuery query) {
        return videoRecordRepository.findById(query.videoId());
    }

    @Override
    public List<VideoRecord> handle(GetAllVideoRecordsByParkingLotQuery query) {
        return videoRecordRepository.findByParkingLotId(query.parkingLotId());
    }
}
