package upc.iot.parkup.iam.domain.model.commands;

import upc.iot.parkup.iam.domain.model.entities.Role;

import java.util.List;
import java.util.Set;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
public record SignUpCommand(String name, String lastname, String username, String email, String password, Set<Role> roles) {
}