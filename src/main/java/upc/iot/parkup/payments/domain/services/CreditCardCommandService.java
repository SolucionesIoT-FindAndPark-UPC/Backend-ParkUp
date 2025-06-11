package upc.iot.parkup.payments.domain.services;

import upc.iot.parkup.payments.domain.model.commands.CreateCreditCardCommand;
import upc.iot.parkup.payments.domain.model.commands.DeleteCreditCardCommand;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;

public interface CreditCardCommandService {
    CreditCard handle(CreateCreditCardCommand command);
    void handle(DeleteCreditCardCommand command);
}
