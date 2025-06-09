package upc.iot.parkup.iam.domain.services;

import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.queries.GetAllUsersQuery;
import upc.iot.parkup.iam.domain.model.queries.GetUserByIdQuery;
import upc.iot.parkup.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}
