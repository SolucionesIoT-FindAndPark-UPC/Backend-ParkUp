package upc.iot.parkup.edgenodes.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.iot.parkup.edgenodes.application.internal.services.commandservices.EdgeNodeCommandService;
import upc.iot.parkup.edgenodes.application.internal.services.queryservices.EdgeNodeQueryService;
import upc.iot.parkup.edgenodes.domain.model.commands.CreateEdgeNodeCommand;
import upc.iot.parkup.edgenodes.domain.model.commands.MakeEdgeNodeRequestCommand;
import upc.iot.parkup.edgenodes.domain.model.commands.UpdateEdgeNodeCommand;
import upc.iot.parkup.edgenodes.domain.model.queries.GetAllNodesQuery;
import upc.iot.parkup.edgenodes.domain.model.queries.GetNodeByIdQuery;
import upc.iot.parkup.edgenodes.interfaces.rest.resources.CreateEdgeNodeResource;
import upc.iot.parkup.edgenodes.interfaces.rest.resources.EdgeNodeResource;
import upc.iot.parkup.edgenodes.interfaces.rest.resources.UpdateEdgeNodeResource;
import upc.iot.parkup.edgenodes.interfaces.rest.transform.EdgeNodeResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/edgenodes")
@Tag(name = "Edge Nodes", description = "Edge Nodes for IoT devices")
public class EdgeNodeController {

    private final EdgeNodeCommandService commandService;
    private final EdgeNodeQueryService queryService;

    public EdgeNodeController(EdgeNodeCommandService commandService, EdgeNodeQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<EdgeNodeResource> createEdgeNode(@Valid @RequestBody CreateEdgeNodeResource resource) {
        var command = new CreateEdgeNodeCommand(resource.url(), resource.port());
        var edgeNode = commandService.handle(command);
        return ResponseEntity.ok(EdgeNodeResourceAssembler.toResource(edgeNode));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EdgeNodeResource> updateEdgeNode(@PathVariable Long id, @Valid @RequestBody UpdateEdgeNodeResource resource) {
        var command = new UpdateEdgeNodeCommand(id, resource.url(), resource.port());
        var edgeNode = commandService.handle(command);
        return ResponseEntity.ok(EdgeNodeResourceAssembler.toResource(edgeNode));
    }

    @GetMapping
    public ResponseEntity<List<EdgeNodeResource>> getAllEdgeNodes() {
        var nodes = queryService.handle(new GetAllNodesQuery())
                .stream()
                .map(EdgeNodeResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(nodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EdgeNodeResource> getEdgeNodeById(@PathVariable Long id) {
        return queryService.handle(new GetNodeByIdQuery(id))
                .map(EdgeNodeResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/request")
    public ResponseEntity<String> makeRequestToNode(
            @PathVariable Long id,
            @RequestParam String endpoint,
            @RequestParam(defaultValue = "GET") HttpMethod method,
            @RequestBody(required = false) String requestBody) {

        try {
            var command = new MakeEdgeNodeRequestCommand(id, endpoint, method, requestBody);
            String response = commandService.handle(command);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error making request: " + e.getMessage());
        }
    }
}
