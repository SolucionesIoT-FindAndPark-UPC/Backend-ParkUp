// EdgeQueryService.java
package upc.iot.parkup.edgenodes.domain.services;

import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;
import upc.iot.parkup.edgenodes.domain.model.queries.GetAllNodesQuery;
import upc.iot.parkup.edgenodes.domain.model.queries.GetNodeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EdgeQueryService {
    List<EdgeNode> handle(GetAllNodesQuery query);
    Optional<EdgeNode> handle(GetNodeByIdQuery query);
}
