package upc.iot.parkup.payments.domain.model.commands;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record CreatePaymentCommand(Long creditCardId, Double amount){}


