package upc.iot.parkup.payments.domain.model.queries;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record GetAllCreditCardsByUserIdQuery(long userId) {

    /**
     * Constructor for GetAllCredirCardsByUserId.
     *
     * @param userId the ID of the user whose credit cards are to be retrieved
     */
    public GetAllCreditCardsByUserIdQuery {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }
    }
}
