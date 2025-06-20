package upc.iot.parkup.parkingcirculation.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.queries.GetUserByUsernameQuery;
import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.domain.model.entities.Vehicle;
import upc.iot.parkup.parkingcirculation.domain.model.queries.GetVehiclesByUserIdQuery;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesCommandService;
import upc.iot.parkup.parkingcirculation.domain.services.VehiclesQueryService;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.RegisterVehicleResource;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.VehicleResource;
import upc.iot.parkup.parkingcirculation.interfaces.rest.transform.RegisterVehicleCommandFromResourceAssembler;
import upc.iot.parkup.parkingcirculation.interfaces.rest.transform.VehicleResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/parking-circulation/vehicles")
@Tag(name = "Parking Circulation - Vehicles")
public class VehiclesController {

    private final VehiclesCommandService vehiclesCommandService;
    private final VehiclesQueryService vehiclesQueryService;
    private final upc.iot.parkup.iam.domain.services.UserQueryService iamUserQueryService;

    public VehiclesController(VehiclesCommandService vehiclesCommandService, VehiclesQueryService vehiclesQueryService, upc.iot.parkup.iam.domain.services.UserQueryService iamUserQueryService) {
        this.vehiclesCommandService = vehiclesCommandService;
        this.vehiclesQueryService = vehiclesQueryService;
        this.iamUserQueryService = iamUserQueryService;
    }

    @PostMapping
    public ResponseEntity<VehicleResource> registerVehicle(@RequestBody RegisterVehicleResource resource, @AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = principal.getUsername();
        Optional<User> iamUserOptional = iamUserQueryService.handle(new GetUserByUsernameQuery(username));
        if (iamUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = iamUserOptional.get().getId();

        RegisterVehicleCommand command = RegisterVehicleCommandFromResourceAssembler.toCommandFromResource(resource, userId);
        Optional<Vehicle> vehicleOptional = vehiclesCommandService.handle(command);

        return vehicleOptional
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<VehicleResource>> getVehiclesByCurrentUser(@AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = principal.getUsername();
        Optional<User> iamUserOptional = iamUserQueryService.handle(new GetUserByUsernameQuery(username));

        if (iamUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = iamUserOptional.get().getId();

        GetVehiclesByUserIdQuery query = new GetVehiclesByUserIdQuery(userId);
        List<Vehicle> vehicles = vehiclesQueryService.handle(query);
        List<VehicleResource> vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicleResources);
    }
}
