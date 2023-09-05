package cz.sinkokri.routing.service.impl;

import cz.sinkokri.routing.client.Client;
import cz.sinkokri.routing.dto.Route;
import cz.sinkokri.routing.service.RoutingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record RoutingServiceImpl(Client client) implements RoutingService {

    @Override
    public Route findRoute(String origin, String destination) {
        log.info("Fetched response {}", client.fetchCountries());
        return null;
    }
}
