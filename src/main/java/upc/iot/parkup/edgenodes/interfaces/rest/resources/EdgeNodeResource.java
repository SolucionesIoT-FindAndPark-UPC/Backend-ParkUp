package upc.iot.parkup.edgenodes.interfaces.rest.resources;
public record EdgeNodeResource(
        Long id,
        String url,
        Integer port
) {}
