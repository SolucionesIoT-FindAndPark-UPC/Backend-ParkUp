package upc.iot.parkup.payments.domain.services;

import upc.iot.parkup.payments.domain.model.aggregates.Payment;
import upc.iot.parkup.payments.domain.model.queries.GetAllPaymentsByUserIdQuery;

import java.util.List;



public interface PaymentQueryService {
    List<Payment> handle(GetAllPaymentsByUserIdQuery query);
}
