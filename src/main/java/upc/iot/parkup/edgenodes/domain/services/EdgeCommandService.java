package upc.iot.parkup.edgenodes.domain.services;


import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;
import upc.iot.parkup.edgenodes.domain.model.commands.CreateEdgeNodeCommand;
import upc.iot.parkup.edgenodes.domain.model.commands.UpdateEdgeNodeCommand;

public interface EdgeCommandService {
    EdgeNode create(CreateEdgeNodeCommand command);
    EdgeNode update(EdgeNode edgeNode, UpdateEdgeNodeCommand command);
}
