package upc.iot.parkup.parkingcirculation.domain.model.commands;

public record ProcessVehicleExitCommand(String licensePlate, Long parkingLotId) {
}
