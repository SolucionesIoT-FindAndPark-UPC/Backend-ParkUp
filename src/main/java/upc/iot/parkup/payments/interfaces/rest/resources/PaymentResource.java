package upc.iot.parkup.payments.interfaces.rest.resources;

import upc.iot.parkup.payments.domain.model.entities.CreditCard;

public record PaymentResource(Double amount, Long creditCardId) {}
