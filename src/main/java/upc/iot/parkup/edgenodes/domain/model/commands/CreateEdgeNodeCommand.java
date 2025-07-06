package upc.iot.parkup.edgenodes.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEdgeNodeCommand(
        @NotBlank String url,
        @NotNull Integer port
) {}
