package upc.iot.parkup.parkingcirculation.application.internal;

import org.springframework.stereotype.Service;
import upc.iot.parkup.parkingcirculation.application.acl.IamContextFacadeForParkingCirculation;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetParkingHistoryByUserIdQuery;
import upc.iot.parkup.parkingcirculation.domain.services.CirculationQueryService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.ParkingRecordRepository;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CirculationQueryServiceImpl implements CirculationQueryService {

    private final ParkingRecordRepository parkingRecordRepository;
    private final VehicleRepository vehicleRepository;
    private final IamContextFacadeForParkingCirculation iamContextFacade;

    public CirculationQueryServiceImpl(ParkingRecordRepository parkingRecordRepository,
                                       VehicleRepository vehicleRepository,
                                       IamContextFacadeForParkingCirculation iamContextFacade) {
        this.parkingRecordRepository = parkingRecordRepository;
        this.vehicleRepository = vehicleRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    public List<ParkingRecord> handle(GetParkingHistoryByUserIdQuery query) {
        if (!iamContextFacade.doesUserExistById(query.userId())) {
            throw new IllegalArgumentException("User with id " + query.userId() + " not found");
        }

        List<Vehicle> vehicles = vehicleRepository.findAllByUserId(query.userId());
        if (vehicles.isEmpty()) {
            return new ArrayList<>();
        }

        List<ParkingRecord> allParkingRecords = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            allParkingRecords.addAll(parkingRecordRepository.findAllByVehicleIdOrderByEntryTimeDesc(vehicle.getId()));
        }

        // The records are already sorted by entryTime desc per vehicle.
        // If a global sort across all records from all vehicles is needed:
        // allParkingRecords.sort((pr1, pr2) -> pr2.getEntryTime().compareTo(pr1.getEntryTime()));

        return allParkingRecords;
    }
}
