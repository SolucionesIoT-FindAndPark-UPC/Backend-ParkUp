package upc.iot.parkup.iam.application.internal.outboundservices.domains;

import org.springframework.stereotype.Service;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.AllowedDomainRepository;

import java.util.HashSet;
import java.util.Set;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
@Service
public class RoleAssignmentService {
    private final AllowedDomainRepository allowedDomainRepository;

    public RoleAssignmentService(AllowedDomainRepository allowedDomainRepository) {
        this.allowedDomainRepository = allowedDomainRepository;
    }

    public Set<String> determineRolesFor(String email) {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_DRIVER");

        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        if (allowedDomainRepository.existsByDomain(domain)) {
            roles.add("ROLE_ADMIN");
        }

        return roles;
    }
}