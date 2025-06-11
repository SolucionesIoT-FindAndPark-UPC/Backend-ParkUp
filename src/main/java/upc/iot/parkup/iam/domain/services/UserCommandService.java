package upc.iot.parkup.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.commands.SignInCommand;
import upc.iot.parkup.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}