package upc.iot.parkup.payments.interfaces.rest.resources;

import upc.iot.parkup.payments.domain.model.entities.CreditCard;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record PaymentResource(Double amount, Long creditCardId) {}
