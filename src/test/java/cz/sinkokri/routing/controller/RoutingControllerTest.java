package cz.sinkokri.routing.controller;

import cz.sinkokri.routing.dto.Route;
import cz.sinkokri.routing.service.RoutingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoutingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private RoutingService routingService;
    private static final String ORIGIN = "CZE";
    private static final String DESTINATION = "ITA";

    @Test
    void getRouteTest() throws Exception {
        var route = mockRoute();
        when(routingService.findRoute(ORIGIN, DESTINATION))
                .thenReturn(route);
        this.mockMvc.perform(get("/routing/" + ORIGIN + "/" + DESTINATION))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.route.length()").value(route.getRoute().size()));
    }

    private Route mockRoute() {
        return Route.builder()
                .route(List.of("CZE", "AUT", "ITA"))
                .build();
    }
}
