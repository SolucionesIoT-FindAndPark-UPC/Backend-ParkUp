package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.commands.CreateCreditCardCommand;
import upc.iot.parkup.payments.interfaces.rest.resources.CreateCreditCardResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class CreateCreditCardFromResourceAssembler {
    public static CreateCreditCardCommand toCommandFromResource(
            CreateCreditCardResource resource) {

        return new CreateCreditCardCommand(
                resource.cardNumber(), resource.cardHolderName(),resource.expirationDate(),resource.cvv(), resource.userId());
    }
}
