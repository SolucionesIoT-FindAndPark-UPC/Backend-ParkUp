package upc.iot.parkup.parkingcirculation.domain.model.commands;

public record ProcessVehicleEntryCommand(String licensePlate, Long parkingLotId) {
}
