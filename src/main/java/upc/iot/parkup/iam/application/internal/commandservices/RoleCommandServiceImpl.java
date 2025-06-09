package upc.iot.parkup.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.domain.model.commands.SeedRolesCommand;
import upc.iot.parkup.iam.domain.model.entities.Role;
import upc.iot.parkup.iam.domain.model.valueobjects.Roles;
import upc.iot.parkup.iam.domain.services.RoleCommandService;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Arrays;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */

=======
>>>>>>> Stashed changes
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
