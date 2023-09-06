package cz.sinkokri.routing.service;

import cz.sinkokri.routing.client.Client;
import cz.sinkokri.routing.dto.Country;
import cz.sinkokri.routing.dto.Route;
import cz.sinkokri.routing.service.impl.RoutingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoutingServiceTest {
    @Mock
    private Client client;
    private RoutingService routingService;
    private static final String ORIGIN = "CZE";
    private static final String DESTINATION = "ITA";

    @BeforeEach
    void setup() {
        routingService = new RoutingServiceImpl(client);
    }

    @Test
    void routingTest() {
        var expectedRoute = mockRoute();
        var countries = mockCountries();
        when(client.fetchCountries()).thenReturn(countries);
        var route = routingService.findRoute(ORIGIN, DESTINATION);
        assertFalse(route.getRoute().isEmpty());
        assertEquals(route.getRoute().size(), expectedRoute.getRoute().size());
        assertEquals(route.getRoute(), expectedRoute.getRoute());
    }

    private Route mockRoute() {
        return Route.builder()
                .route(List.of("CZE", "AUT", "ITA"))
                .build();
    }

    private List<Country> mockCountries() {
        return List.of(Country.builder()
                        .borderCountries(List.of(
                                "AUT",
                                "BEL",
                                "CZE",
                                "DNK",
                                "FRA",
                                "LUX",
                                "NLD",
                                "POL",
                                "CHE"))
                        .countryCode("CZE")
                .build(),
                Country.builder()
                        .borderCountries(List.of(
                                "CZE",
                                "DEU",
                                "HUN",
                                "ITA",
                                "LIE",
                                "SVK",
                                "SVN",
                                "CHE"))
                        .countryCode("AUT")
                        .build(),
                Country.builder()
                        .borderCountries(List.of(
                                "AUT",
                                "FRA",
                                "SMR",
                                "SVN",
                                "CHE",
                                "VAT"))
                        .countryCode("ITA")
                        .build());
    }
}
