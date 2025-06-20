package upc.iot.parkup.parkingcirculation.application.acl;

import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.domain.model.queries.GetUserByIdQuery;
import upc.iot.parkup.iam.domain.services.UserQueryService;

@Service
public class IamContextFacadeForParkingCirculation {

    private final UserQueryService userQueryService;

    public IamContextFacadeForParkingCirculation(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * Checks if a user exists in the IAM context by their ID.
     * @param userId The ID of the user to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean existsUserById(Long userId) {
        if (userId == null) {
            return false;
        }
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        return user.isPresent();
    }
}
