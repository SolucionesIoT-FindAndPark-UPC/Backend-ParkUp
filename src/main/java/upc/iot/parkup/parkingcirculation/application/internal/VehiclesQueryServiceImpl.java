package upc.iot.parkup.parkingcirculation.application.internal;

import org.springframework.stereotype.Service;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehicleByLicensePlateQuery;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehiclesByUserIdQuery;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesQueryService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesQueryServiceImpl implements VehiclesQueryService {

    private final VehicleRepository vehicleRepository;

    public VehiclesQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByLicensePlateQuery query) {
        return vehicleRepository.findByLicensePlate(query.licensePlate());
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByUserIdQuery query) {
        return vehicleRepository.findAllByUserId(query.userId());
    }
}
