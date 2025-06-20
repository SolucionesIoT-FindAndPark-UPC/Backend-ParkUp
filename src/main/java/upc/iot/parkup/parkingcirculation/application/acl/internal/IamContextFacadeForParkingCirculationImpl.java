package upc.iot.parkup.parkingcirculation.application.acl.internal;

import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.queries.GetUserByIdQuery;
import upc.iot.parkup.iam.domain.services.UserQueryService;
import upc.iot.parkup.parkingcirculation.application.acl.IamContextFacadeForParkingCirculation;
import upc.iot.parkup.parkingcirculation.domain.model.valueobjects.UserId;

import java.util.Optional;

@Service
public class IamContextFacadeForParkingCirculationImpl implements IamContextFacadeForParkingCirculation {

    private final UserQueryService userQueryService;

    public IamContextFacadeForParkingCirculationImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public boolean doesUserExistById(Long userId) {
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(userId));
        return user.isPresent();
    }

    @Override
    public Optional<UserId> getUserById(Long userId) {
        Optional<User> userOptional = userQueryService.handle(new GetUserByIdQuery(userId));
        return userOptional.map(user -> new UserId(user.getId()));
    }
}
