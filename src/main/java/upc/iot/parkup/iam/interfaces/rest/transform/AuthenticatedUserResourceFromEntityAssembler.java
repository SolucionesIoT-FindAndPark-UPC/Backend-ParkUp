package upc.iot.parkup.iam.interfaces.rest.transform;

import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.interfaces.rest.resources.AuthenticatedUserResource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}