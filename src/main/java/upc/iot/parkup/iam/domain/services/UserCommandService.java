package upc.iot.parkup.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.commands.SignInCommand;
import upc.iot.parkup.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}