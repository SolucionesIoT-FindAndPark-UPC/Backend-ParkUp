package upc.iot.parkup.iam.interfaces.rest.transform;

import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.entities.Role;
import upc.iot.parkup.iam.interfaces.rest.resources.UserResource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), roles);
    }
}