package upc.iot.parkup.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.application.internal.outboundservices.domains.RoleAssignmentService;
import upc.iot.parkup.iam.application.internal.outboundservices.hashing.HashingService;
import upc.iot.parkup.iam.application.internal.outboundservices.tokens.TokenService;
import upc.iot.parkup.iam.domain.model.aggregates.User;
import upc.iot.parkup.iam.domain.model.commands.SignInCommand;
import upc.iot.parkup.iam.domain.model.commands.SignUpCommand;
import upc.iot.parkup.iam.domain.model.entities.Role;
import upc.iot.parkup.iam.domain.model.valueobjects.Roles;
import upc.iot.parkup.iam.domain.services.UserCommandService;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */


=======
>>>>>>> Stashed changes
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final RoleAssignmentService roleAssignmentService;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService,
                                  RoleRepository roleRepository,
                                  RoleAssignmentService roleAssignmentService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.roleAssignmentService = roleAssignmentService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        Set<String> requestedRoles = command.roles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        Set<String> allRoles = Stream.concat(
                requestedRoles.stream(),
                roleAssignmentService.determineRolesFor(command.username()).stream()
        ).collect(Collectors.toCollection(HashSet::new));
        allRoles.addAll(roleAssignmentService.determineRolesFor(command.username()));

        var roles = allRoles.stream()
                .map(roleName -> roleRepository.findByName(Roles.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());



        var user = new User(command.name(), command.lastname(), command.username(), command.email(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }


    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(new ImmutablePair<>(user, token));

    }

}
