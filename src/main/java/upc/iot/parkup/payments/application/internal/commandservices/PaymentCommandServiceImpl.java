package upc.iot.parkup.payments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.iot.parkup.payments.domain.model.aggregates.Payment;
import upc.iot.parkup.payments.domain.model.commands.CreatePaymentCommand;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;
import upc.iot.parkup.payments.domain.services.PaymentCommandService;
import upc.iot.parkup.payments.infrastructure.persistence.jpa.repositories.CreditCardRepository;
import upc.iot.parkup.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;


@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final CreditCardRepository creditCardRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, CreditCardRepository creditCardRepository) {
        this.paymentRepository = paymentRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public Payment handle(CreatePaymentCommand command) {
        CreditCard card = creditCardRepository.findById(command.creditCardId())
                .orElseThrow(() -> new IllegalArgumentException("Credit card not found"));
        Payment payment = new Payment(command.amount(), card);
        return paymentRepository.save(payment);
    }
}

