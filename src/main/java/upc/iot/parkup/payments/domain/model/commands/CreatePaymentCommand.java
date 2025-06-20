package upc.iot.parkup.payments.domain.model.commands;

public record CreatePaymentCommand(Long creditCardId, Double amount){}


