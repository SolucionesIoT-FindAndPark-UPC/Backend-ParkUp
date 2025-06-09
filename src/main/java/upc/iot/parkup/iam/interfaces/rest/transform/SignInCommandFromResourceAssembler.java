package upc.iot.parkup.iam.interfaces.rest.transform;

import upc.iot.parkup.iam.domain.model.commands.SignInCommand;
import upc.iot.parkup.iam.interfaces.rest.resources.SignInResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}