package upc.iot.parkup.parkingcirculation.application.acl;

import upc.iot.parkup.parkingcirculation.domain.model.valueobjects.UserId;
import java.util.Optional;

public interface IamContextFacadeForParkingCirculation {
    boolean doesUserExistById(Long userId);
    Optional<UserId> getUserById(Long userId);
}
