package upc.iot.parkup.iam.domain.services;

import upc.iot.parkup.iam.domain.model.commands.SeedRolesCommand;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
