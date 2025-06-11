package upc.iot.parkup.payments.interfaces.rest.resources;

import upc.iot.parkup.iam.domain.model.aggregates.User;


public record CreditCardResource(Long creditCardId,String cardNumber, String cardHolderName, String expirationDate, String cvv,  Long userId)  {
}
