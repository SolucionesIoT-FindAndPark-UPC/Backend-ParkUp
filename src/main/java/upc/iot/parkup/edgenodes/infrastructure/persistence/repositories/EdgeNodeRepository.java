package upc.iot.parkup.edgenodes.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;

public interface EdgeNodeRepository extends JpaRepository<EdgeNode, Long> {
}
