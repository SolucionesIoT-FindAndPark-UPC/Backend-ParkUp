package upc.iot.parkup.parkingcirculation.domain.services;

import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetParkingHistoryByUserIdQuery;
import java.util.List;

public interface CirculationQueryService {
    List<ParkingRecord> handle(GetParkingHistoryByUserIdQuery query);
}
