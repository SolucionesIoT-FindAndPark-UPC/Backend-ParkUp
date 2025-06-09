package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.commands.DeleteCreditCardCommand;
import upc.iot.parkup.payments.interfaces.rest.resources.DeleteCreditCardResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class DeleteCreditCardFromResourceAssembler {
    public static DeleteCreditCardCommand toCommandFromResource(DeleteCreditCardResource resource) {
        return new DeleteCreditCardCommand(resource.creditCardId());
    }
}
