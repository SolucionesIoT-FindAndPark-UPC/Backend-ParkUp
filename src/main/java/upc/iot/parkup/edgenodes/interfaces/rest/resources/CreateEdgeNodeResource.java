package upc.iot.parkup.edgenodes.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEdgeNodeResource(
        @NotBlank String url,
        @NotNull Integer port
) {}