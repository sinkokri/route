package cz.sinkokri.routing.controller;


import cz.sinkokri.routing.dto.Route;
import cz.sinkokri.routing.service.RoutingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/routing")
public record RouteController(RoutingService routingService) {

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<Route> getRoute(@PathVariable String origin, @PathVariable String destination) {
        log.info("Received getRoute with origin {} and destination {}", origin, destination);
        var route = routingService.findRoute(origin, destination);
        return route.getRoute().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(route);
    }
}
