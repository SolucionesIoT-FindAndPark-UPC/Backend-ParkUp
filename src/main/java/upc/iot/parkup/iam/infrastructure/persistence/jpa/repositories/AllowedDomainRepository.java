package upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.iot.parkup.iam.domain.model.entities.AllowedDomain;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes
public interface AllowedDomainRepository extends JpaRepository<AllowedDomain, Long> {
    boolean existsByDomain(String domain);
}