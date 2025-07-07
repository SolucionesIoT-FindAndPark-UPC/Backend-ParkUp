package upc.iot.parkup.edgenodes.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import upc.iot.parkup.edgenodes.domain.model.queries.GetAllNodesQuery;
import upc.iot.parkup.edgenodes.domain.model.queries.GetNodeByIdQuery;
import upc.iot.parkup.edgenodes.domain.services.EdgeQueryService;

import java.sql.Timestamp;

@Service
public class EdgeNodeStartupHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdgeNodeStartupHandler.class);
    private final EdgeQueryService queryDomainService;

    public EdgeNodeStartupHandler(@Qualifier("edgeNodeQueryService") EdgeQueryService queryService) {
        this.queryDomainService = queryService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Checking edge nodes on startup for {} at {}", applicationName, getCurrentTimestamp());

        var nodes = queryDomainService.handle(new GetAllNodesQuery());
        LOGGER.info("There are {} edge nodes registered.", nodes.size());

        var nodeIdToQuery = 1L;
        var optionalNode = queryDomainService.handle(new GetNodeByIdQuery(nodeIdToQuery));
        if (optionalNode.isPresent()) {
            var node = optionalNode.get();
            LOGGER.info("Node ID {} found: URL={}, Port={}", nodeIdToQuery, node.getUrl(), node.getPort());
        } else {
            LOGGER.warn("Node ID {} not found", nodeIdToQuery);
        }
    }


    private Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
