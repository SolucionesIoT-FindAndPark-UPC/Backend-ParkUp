package upc.iot.parkup.payments.domain.services;

import upc.iot.parkup.payments.domain.model.aggregates.Payment;
import upc.iot.parkup.payments.domain.model.queries.GetAllPaymentsByUserIdQuery;

import java.util.List;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes

public interface PaymentQueryService {
    List<Payment> handle(GetAllPaymentsByUserIdQuery query);
}
