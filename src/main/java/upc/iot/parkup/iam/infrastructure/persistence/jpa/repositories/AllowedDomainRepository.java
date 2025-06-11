package upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.iot.parkup.iam.domain.model.entities.AllowedDomain;


public interface AllowedDomainRepository extends JpaRepository<AllowedDomain, Long> {
    boolean existsByDomain(String domain);
}