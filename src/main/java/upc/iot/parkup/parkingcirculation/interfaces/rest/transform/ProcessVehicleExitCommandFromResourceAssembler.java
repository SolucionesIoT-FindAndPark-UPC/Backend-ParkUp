package upc.iot.parkup.parkingcirculation.interfaces.rest.transform;

import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleExitCommand;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.ProcessPlateResource;

public class ProcessVehicleExitCommandFromResourceAssembler {
    public static ProcessVehicleExitCommand toCommandFromResource(ProcessPlateResource resource) {
        return new ProcessVehicleExitCommand(resource.licensePlate(), resource.parkingLotId());
    }
}
