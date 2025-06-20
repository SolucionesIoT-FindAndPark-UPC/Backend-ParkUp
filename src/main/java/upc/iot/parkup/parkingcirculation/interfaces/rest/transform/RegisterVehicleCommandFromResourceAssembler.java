package upc.iot.parkup.parkingcirculation.interfaces.rest.transform;

import upc.iot.parkup.parkingcirculation.domain.model.commands.RegisterVehicleCommand;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.RegisterVehicleResource;

public class RegisterVehicleCommandFromResourceAssembler {
    public static RegisterVehicleCommand toCommandFromResource(RegisterVehicleResource resource, Long userId) {
        return new RegisterVehicleCommand(resource.licensePlate(), userId);
    }
}
