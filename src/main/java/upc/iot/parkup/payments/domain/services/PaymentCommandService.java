package upc.iot.parkup.payments.domain.services;

import upc.iot.parkup.payments.domain.model.aggregates.Payment;
import upc.iot.parkup.payments.domain.model.commands.CreatePaymentCommand;


public interface PaymentCommandService {
    Payment handle(CreatePaymentCommand command);
}