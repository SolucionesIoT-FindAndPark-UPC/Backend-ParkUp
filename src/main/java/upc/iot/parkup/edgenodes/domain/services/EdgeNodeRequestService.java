package upc.iot.parkup.edgenodes.domain.services;

import org.springframework.http.HttpMethod;
import upc.iot.parkup.edgenodes.domain.model.commands.MakeEdgeNodeRequestCommand;

public interface EdgeNodeRequestService {
    String makeRequest(MakeEdgeNodeRequestCommand command);
}