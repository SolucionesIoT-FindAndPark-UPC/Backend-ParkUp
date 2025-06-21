package upc.iot.parkup.camerafeed.domain.services;

import upc.iot.parkup.camerafeed.domain.model.entities.VideoRecord;
import upc.iot.parkup.camerafeed.domain.model.queries.GetAllVideoRecordsByParkingLotQuery;
import upc.iot.parkup.camerafeed.domain.model.queries.GetVideoRecordByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VideoQueryService {
    Optional<VideoRecord> handle(GetVideoRecordByIdQuery query);
    List<VideoRecord> handle(GetAllVideoRecordsByParkingLotQuery query);
}
