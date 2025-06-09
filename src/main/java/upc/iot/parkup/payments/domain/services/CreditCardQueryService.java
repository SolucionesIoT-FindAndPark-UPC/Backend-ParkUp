package upc.iot.parkup.payments.domain.services;

import upc.iot.parkup.payments.domain.model.entities.CreditCard;
import upc.iot.parkup.payments.domain.model.queries.GetAllCreditCardsByUserIdQuery;
import upc.iot.parkup.payments.domain.model.queries.GetCreditCardByIdQuery;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface CreditCardQueryService {
    List<CreditCard> handle(GetAllCreditCardsByUserIdQuery query);
    CreditCard handle(GetCreditCardByIdQuery query);
}

