package upc.iot.parkup.payments.application.internal.commandservices;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.payments.domain.model.commands.CreateCreditCardCommand;
import upc.iot.parkup.payments.domain.model.commands.DeleteCreditCardCommand;
import upc.iot.parkup.payments.domain.services.CreditCardCommandService;
import upc.iot.parkup.payments.infrastructure.persistence.jpa.repositories.CreditCardRepository;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.UserRepository;
<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
@Service
public class CreditCardCommandServiceImpl implements CreditCardCommandService {

    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;

    public CreditCardCommandServiceImpl(CreditCardRepository creditCardRepository,
                                        UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository = userRepository;
    }


    @Override
    public CreditCard handle(CreateCreditCardCommand command) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CreditCard card = new CreditCard();
        card.setExpirationDate(command.expirationDate());
        card.setCardNumber(command.cardNumber());
        card.setCvv(command.cvv());
        card.setCardHolderName(command.cardHolderName());
        card.setUser(user);

        return creditCardRepository.save(card);
    }


    @Override
    public void handle(DeleteCreditCardCommand command) {
        creditCardRepository.deleteById(command.creditCardId());
    }
}