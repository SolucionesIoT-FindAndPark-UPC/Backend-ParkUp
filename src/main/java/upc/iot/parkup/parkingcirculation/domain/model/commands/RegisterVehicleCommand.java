package upc.iot.parkup.parkingcirculation.domain.model.commands;

public record RegisterVehicleCommand(String licensePlate, Long userId) {
}
