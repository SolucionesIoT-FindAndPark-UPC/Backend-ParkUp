package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.commands.CreatePaymentCommand;
import upc.iot.parkup.payments.interfaces.rest.resources.CreatePaymentResource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public class CreatePaymentFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(resource.creditCardId(), resource.amount());
    }
}
