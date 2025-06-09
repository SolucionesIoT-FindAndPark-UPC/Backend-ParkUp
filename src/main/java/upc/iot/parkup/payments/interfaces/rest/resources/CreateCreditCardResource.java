package upc.iot.parkup.payments.interfaces.rest.resources;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public record CreateCreditCardResource(String cardNumber, String cardHolderName, String expirationDate, String cvv, Long userId) {
    public CreateCreditCardResource {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number cannot be null or blank");
        }
        if (cardHolderName == null || cardHolderName.isBlank()) {
            throw new IllegalArgumentException("Card holder name cannot be null or blank");
        }
        if (expirationDate == null || expirationDate.isBlank()) {
            throw new IllegalArgumentException("Expiration date cannot be null or blank");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV cannot be null or blank");
        }
    }
}
