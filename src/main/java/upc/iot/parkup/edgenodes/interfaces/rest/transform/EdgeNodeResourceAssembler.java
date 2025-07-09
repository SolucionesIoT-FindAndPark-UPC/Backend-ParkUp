package upc.iot.parkup.edgenodes.interfaces.rest.transform;


import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;
import upc.iot.parkup.edgenodes.interfaces.rest.resources.EdgeNodeResource;

public class EdgeNodeResourceAssembler {

    public static EdgeNodeResource toResource(EdgeNode edgeNode) {
        return new EdgeNodeResource(
                edgeNode.getId(),
                edgeNode.getUrl(),
                edgeNode.getPort()
        );
    }
}