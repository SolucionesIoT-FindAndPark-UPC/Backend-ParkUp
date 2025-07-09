package upc.iot.parkup.edgenodes.application.internal.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upc.iot.parkup.edgenodes.domain.model.aggregates.EdgeNode;
import upc.iot.parkup.edgenodes.domain.model.commands.MakeEdgeNodeRequestCommand;
import upc.iot.parkup.edgenodes.domain.services.EdgeNodeRequestService;
import upc.iot.parkup.edgenodes.infrastructure.persistence.repositories.EdgeNodeRepository;

@Service
public class EdgeNodeRequestServiceImpl implements EdgeNodeRequestService {

    private final RestTemplate restTemplate;
    private final EdgeNodeRepository edgeNodeRepository;

    public EdgeNodeRequestServiceImpl(RestTemplate restTemplate,
                                      EdgeNodeRepository edgeNodeRepository) {
        this.restTemplate = restTemplate;
        this.edgeNodeRepository = edgeNodeRepository;
    }

    @Override
    public String makeRequest(MakeEdgeNodeRequestCommand command) {
        EdgeNode node = edgeNodeRepository.findById(command.nodeId())
                .orElseThrow(() -> new IllegalArgumentException("EdgeNode not found with ID: " + command.nodeId()));

        String fullUrl = buildFullUrl(node, command.endpoint());
        HttpEntity<String> entity = createHttpEntity(command.requestBody());

        ResponseEntity<String> response = restTemplate.exchange(
                fullUrl,
                command.method(),
                entity,
                String.class
        );

        return response.getBody();
    }

    private String buildFullUrl(EdgeNode node, String endpoint) {
        return "http://" + node.getUrl() + ":" + node.getPort() +
                (endpoint.startsWith("/") ? endpoint : "/" + endpoint);
    }

    private HttpEntity<String> createHttpEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}