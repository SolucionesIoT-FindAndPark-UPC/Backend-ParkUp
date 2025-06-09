package upc.iot.parkup.payments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findAllByUser_Id(Long userId);
}