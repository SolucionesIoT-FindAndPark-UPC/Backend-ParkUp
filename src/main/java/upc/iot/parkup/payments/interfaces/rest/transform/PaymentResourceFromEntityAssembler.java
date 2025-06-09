package upc.iot.parkup.payments.interfaces.rest.transform;

import upc.iot.parkup.payments.domain.model.aggregates.Payment;
import upc.iot.parkup.payments.interfaces.rest.resources.PaymentResource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment payment) {
        return new PaymentResource(
                payment.getAmount(),
                payment.getCreditCardId() != null ? payment.getCreditCardId() : null
        );
    }
}
