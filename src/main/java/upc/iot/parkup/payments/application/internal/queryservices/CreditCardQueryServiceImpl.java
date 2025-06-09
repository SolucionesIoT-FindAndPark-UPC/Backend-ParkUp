package upc.iot.parkup.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;
import upc.iot.parkup.payments.domain.model.queries.GetAllCreditCardsByUserIdQuery;
import upc.iot.parkup.payments.domain.model.queries.GetCreditCardByIdQuery;
import upc.iot.parkup.payments.domain.services.CreditCardQueryService;
import upc.iot.parkup.payments.infrastructure.persistence.jpa.repositories.CreditCardRepository;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Service
public class CreditCardQueryServiceImpl implements CreditCardQueryService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardQueryServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCard> handle(GetAllCreditCardsByUserIdQuery query) {
        return creditCardRepository.findAllByUser_Id(query.userId());
    }

    @Override
    public CreditCard handle(GetCreditCardByIdQuery query) {
        return creditCardRepository.findById(query.creditCardId())
                .orElseThrow(() -> new IllegalArgumentException("Credit card not found"));
    }
}
