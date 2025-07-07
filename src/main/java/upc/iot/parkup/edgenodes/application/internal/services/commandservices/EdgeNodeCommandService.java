package upc.iot.parkup.edgenodes.application.internal.services.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;
import upc.iot.parkup.edgenodes.domain.model.commands.CreateEdgeNodeCommand;
import upc.iot.parkup.edgenodes.domain.model.commands.UpdateEdgeNodeCommand;
import upc.iot.parkup.edgenodes.infrastructure.persistence.repositories.EdgeNodeRepository;

@Service
@Transactional
public class EdgeNodeCommandService {

    private final EdgeNodeRepository edgeNodeRepository;

    public EdgeNodeCommandService(EdgeNodeRepository edgeNodeRepository) {
        this.edgeNodeRepository = edgeNodeRepository;
    }

    public EdgeNode handle(CreateEdgeNodeCommand command) {
        var edgeNode = new EdgeNode(command.url(), command.port());
        return edgeNodeRepository.save(edgeNode);
    }

    public EdgeNode handle(UpdateEdgeNodeCommand command) {
        var existing = edgeNodeRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("EdgeNode not found with ID: " + command.id()));

        existing.updateUrlAndPort(command.url(), command.port());
        return edgeNodeRepository.save(existing);
    }
}

