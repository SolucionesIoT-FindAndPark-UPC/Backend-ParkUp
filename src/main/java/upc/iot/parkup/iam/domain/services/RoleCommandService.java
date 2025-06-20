package upc.iot.parkup.iam.domain.services;

import upc.iot.parkup.iam.domain.model.commands.SeedRolesCommand;


public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
