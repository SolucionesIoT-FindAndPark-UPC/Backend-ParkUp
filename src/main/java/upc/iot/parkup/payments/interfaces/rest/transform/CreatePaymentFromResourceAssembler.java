package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.commands.CreatePaymentCommand;
import upc.iot.parkup.payments.interfaces.rest.resources.CreatePaymentResource;


public class CreatePaymentFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(resource.creditCardId(), resource.amount());
    }
}
