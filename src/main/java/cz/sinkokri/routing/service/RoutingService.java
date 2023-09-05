package cz.sinkokri.routing.service;

import cz.sinkokri.routing.dto.Route;


public interface RoutingService {

    Route findRoute(String origin, String destination);

}
