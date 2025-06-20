package upc.iot.parkup.parkingcirculation.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.queries.GetUserByUsernameQuery;
import upc.iot.parkup.parkingcirculation.domain.model.entities.ParkingRecord;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetParkingHistoryByUserIdQuery;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehicleByLicensePlateQuery;
import upc.iot.parkup.parkingcirculation.domain.services.CirculationCommandService;
import upc.iot.parkup.parkingcirculation.domain.services.CirculationQueryService;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesQueryService;
import upc.iot.parkup.parkingcirculation.infrastructure.persistence.jpa.ParkingRecordRepository;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.ParkingRecordResource;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.ProcessPlateResource;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.ProcessPlateResponse;
import upc.iot.parkup.parkingcirculation.interfaces.rest.transform.ParkingRecordResourceFromEntityAssembler;
import upc.iot.parkup.parkingcirculation.interfaces.rest.transform.ProcessVehicleEntryCommandFromResourceAssembler;
import upc.iot.parkup.parkingcirculation.interfaces.rest.transform.ProcessVehicleExitCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/parking-circulation/parking")
@Tag(name = "Parking Circulation - Circulation")
public class CirculationController {

    private final CirculationCommandService circulationCommandService;
    private final CirculationQueryService circulationQueryService;
    private final VehiclesQueryService vehiclesQueryService;
    private final ParkingRecordRepository parkingRecordRepository; // Direct repository use for status check
    private final upc.iot.parkup.iam.domain.services.UserQueryService iamUserQueryService;

    public CirculationController(CirculationCommandService circulationCommandService,
                                 CirculationQueryService circulationQueryService,
                                 VehiclesQueryService vehiclesQueryService,
                                 ParkingRecordRepository parkingRecordRepository,
                                 upc.iot.parkup.iam.domain.services.UserQueryService iamUserQueryService) {
        this.circulationCommandService = circulationCommandService;
        this.circulationQueryService = circulationQueryService;
        this.vehiclesQueryService = vehiclesQueryService;
        this.parkingRecordRepository = parkingRecordRepository;
        this.iamUserQueryService = iamUserQueryService;
    }

    @PostMapping("/process-plate")
    public ResponseEntity<ProcessPlateResponse> processPlateAction(@RequestBody ProcessPlateResource resource) {
        Optional<Vehicle> vehicleOptional = vehiclesQueryService.handle(new GetVehicleByLicensePlateQuery(resource.licensePlate()));
        if (vehicleOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ProcessPlateResponse(false, "Vehicle with license plate " + resource.licensePlate() + " not registered."));
        }
        Vehicle vehicle = vehicleOptional.get();
        Long vehicleId = vehicle.getId();

        Optional<ParkingRecord> existingRecord = parkingRecordRepository.findByVehicleIdAndStatus(vehicleId, "IN_PROGRESS");

        try {
            if (existingRecord.isPresent()) { // Vehicle is exiting
                var exitCommand = ProcessVehicleExitCommandFromResourceAssembler.toCommandFromResource(resource);
                circulationCommandService.handle(exitCommand);
                return ResponseEntity.ok(new ProcessPlateResponse(true, "Vehicle " + resource.licensePlate() + " exited successfully from parking lot " + resource.parkingLotId()));
            } else { // Vehicle is entering
                var entryCommand = ProcessVehicleEntryCommandFromResourceAssembler.toCommandFromResource(resource);
                circulationCommandService.handle(entryCommand);
                return ResponseEntity.ok(new ProcessPlateResponse(true, "Vehicle " + resource.licensePlate() + " entered successfully into parking lot " + resource.parkingLotId()));
            }
        } catch (Exception e) {
            // Consider logging the exception e
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ProcessPlateResponse(false, "Error processing plate: " + e.getMessage()));
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<ParkingRecordResource>> getParkingHistoryForCurrentUser(@AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = principal.getUsername();
        Optional<User> iamUserOptional = iamUserQueryService.handle(new GetUserByUsernameQuery(username));

        if (iamUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = iamUserOptional.get().getId();

        var query = new GetParkingHistoryByUserIdQuery(userId);
        var parkingRecords = circulationQueryService.handle(query);
        var parkingRecordResources = parkingRecords.stream()
                .map(ParkingRecordResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parkingRecordResources);
    }
}
