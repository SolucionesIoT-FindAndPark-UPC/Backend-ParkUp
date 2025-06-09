package upc.iot.parkup.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.queries.GetAllUsersQuery;
import upc.iot.parkup.iam.domain.model.queries.GetUserByIdQuery;
import upc.iot.parkup.iam.domain.model.queries.GetUserByUsernameQuery;
import upc.iot.parkup.iam.domain.services.UserQueryService;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}