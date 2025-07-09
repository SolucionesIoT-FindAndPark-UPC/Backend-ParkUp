package upc.iot.parkup.edgenodes.domain.model.commands;

import org.springframework.http.HttpMethod;

public record MakeEdgeNodeRequestCommand(
        Long nodeId,
        String endpoint,
        HttpMethod method,
        String requestBody
) {}