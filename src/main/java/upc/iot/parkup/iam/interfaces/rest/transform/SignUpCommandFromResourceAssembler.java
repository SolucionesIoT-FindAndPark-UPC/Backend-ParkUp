package upc.iot.parkup.iam.interfaces.rest.transform;

import upc.iot.parkup.iam.domain.model.commands.SignUpCommand;
import upc.iot.parkup.iam.domain.model.entities.Role;
import upc.iot.parkup.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        Set<Role> roles = resource.roles() != null
                ? resource.roles().stream()
                .map(Role::toRoleFromName)
                .collect(Collectors.toSet())
                : new HashSet<>();

        System.out.print("Roles: ");
        System.out.println(!roles.isEmpty() ? roles.getClass().getName().lines() : "No roles");

        return new SignUpCommand(resource.name(), resource.lastname(), resource.username(),  resource.email(),resource.password(), roles);
    }
}
