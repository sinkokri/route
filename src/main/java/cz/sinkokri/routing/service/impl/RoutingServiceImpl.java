package cz.sinkokri.routing.service.impl;

import cz.sinkokri.routing.client.Client;
import cz.sinkokri.routing.dto.Graph;
import cz.sinkokri.routing.dto.Route;
import cz.sinkokri.routing.service.RoutingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record RoutingServiceImpl(Client client) implements RoutingService {

    @Override
    public Route findRoute(String origin, String destination) {
        var countries = client.fetchCountries();
        log.info("Fetched countries {}", countries);

        var graph = Graph.builder().build();
        countries.forEach(graph::addNode);

        return Route.builder()
                .route(graph.bfsSearch(origin, destination))
                .build();
    }
}
