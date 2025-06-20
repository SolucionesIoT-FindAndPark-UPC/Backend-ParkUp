package upc.iot.parkup.payments.interfaces.rest.transform;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;

import upc.iot.parkup.payments.interfaces.rest.resources.CreditCardResource;



public class CreditCardResourceFromEntityAssembler {
    public static CreditCardResource toResourceFromEntity(CreditCard creditCard) {
        return new CreditCardResource(
                creditCard.getId(),
                creditCard.getCardNumber(),
                creditCard.getCardHolderName(),
                creditCard.getExpirationDate(),
                creditCard.getCvv(),
                creditCard.getUserId()
        );
    }
}

