// EdgeNodeQueryService.java
package upc.iot.parkup.edgenodes.application.internal.services.queryservices;

import org.springframework.stereotype.Service;
import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;
import upc.iot.parkup.edgenodes.domain.model.queries.GetAllNodesQuery;
import upc.iot.parkup.edgenodes.domain.model.queries.GetNodeByIdQuery;
import upc.iot.parkup.edgenodes.domain.services.EdgeQueryService;
import upc.iot.parkup.edgenodes.infrastructure.persistence.repositories.EdgeNodeRepository;

import java.util.List;
import java.util.Optional;

@Service("edgeNodeQueryService")
public class EdgeNodeQueryService implements EdgeQueryService {

    private final EdgeNodeRepository repository;

    public EdgeNodeQueryService(EdgeNodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EdgeNode> handle(GetAllNodesQuery query) {
        return repository.findAll();
    }

    @Override
    public Optional<EdgeNode> handle(GetNodeByIdQuery query) {
        return repository.findById(query.id());
    }
}
