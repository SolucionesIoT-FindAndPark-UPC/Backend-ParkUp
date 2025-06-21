package upc.edu.artcollab.api.camerafeed.domain.services;

import upc.edu.artcollab.api.camerafeed.domain.model.entities.VideoRecord;
import upc.edu.artcollab.api.camerafeed.domain.model.queries.GetAllVideoRecordsByParkingLotQuery;
import upc.edu.artcollab.api.camerafeed.domain.model.queries.GetVideoRecordByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VideoQueryService {
    Optional<VideoRecord> handle(GetVideoRecordByIdQuery query);
    List<VideoRecord> handle(GetAllVideoRecordsByParkingLotQuery query);
}
