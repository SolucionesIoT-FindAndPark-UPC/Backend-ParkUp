package upc.iot.parkup.iam.interfaces.rest.transform;

import upc.iot.parkup.iam.domain.model.entities.Role;
import upc.iot.parkup.iam.interfaces.rest.resources.RoleResource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());
    }
}
