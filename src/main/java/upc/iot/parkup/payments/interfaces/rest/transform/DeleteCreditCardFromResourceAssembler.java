package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.commands.DeleteCreditCardCommand;
import upc.iot.parkup.payments.interfaces.rest.resources.DeleteCreditCardResource;

public class DeleteCreditCardFromResourceAssembler {
    public static DeleteCreditCardCommand toCommandFromResource(DeleteCreditCardResource resource) {
        return new DeleteCreditCardCommand(resource.creditCardId());
    }
}
