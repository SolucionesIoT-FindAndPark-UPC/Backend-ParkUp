package upc.iot.parkup.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.iot.parkup.payments.domain.model.aggregates.Payment;
import upc.iot.parkup.payments.domain.model.queries.GetAllPaymentsByUserIdQuery;
import upc.iot.parkup.payments.domain.services.PaymentQueryService;
import upc.iot.parkup.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;

import java.util.List;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(GetAllPaymentsByUserIdQuery query) {
        return paymentRepository.findAllByCreditCard_User_Id(query.userId());
    }
}

