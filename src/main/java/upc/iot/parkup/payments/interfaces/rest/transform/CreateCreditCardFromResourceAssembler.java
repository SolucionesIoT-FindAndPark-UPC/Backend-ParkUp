package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.commands.CreateCreditCardCommand;
import upc.iot.parkup.payments.interfaces.rest.resources.CreateCreditCardResource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public class CreateCreditCardFromResourceAssembler {
    public static CreateCreditCardCommand toCommandFromResource(
            CreateCreditCardResource resource) {

        return new CreateCreditCardCommand(
                resource.cardNumber(), resource.cardHolderName(),resource.expirationDate(),resource.cvv(), resource.userId());
    }
}
