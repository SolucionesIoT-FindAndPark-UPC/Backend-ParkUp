package upc.iot.parkup.parkingcirculation.interfaces.rest.transform;

import upc.iot.parkup.parkingcirculation.domain.model.commands.ProcessVehicleEntryCommand;
import upc.iot.parkup.parkingcirculation.interfaces.rest.resources.ProcessPlateResource;

public class ProcessVehicleEntryCommandFromResourceAssembler {
    public static ProcessVehicleEntryCommand toCommandFromResource(ProcessPlateResource resource) {
        return new ProcessVehicleEntryCommand(resource.licensePlate(), resource.parkingLotId());
    }
}
