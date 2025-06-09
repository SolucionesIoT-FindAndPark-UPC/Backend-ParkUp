package upc.iot.parkup.payments.interfaces.rest.resources;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public record CreatePaymentResource(Long creditCardId, Double amount) {

    public CreatePaymentResource {
        if (creditCardId == null || creditCardId <= 0) {
            throw new IllegalArgumentException("Credit card ID must be a positive number.");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number.");
        }
    }
}
