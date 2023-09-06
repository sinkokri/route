package cz.sinkokri.routing.service;

import cz.sinkokri.routing.client.Client;
import cz.sinkokri.routing.service.impl.RoutingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoutingServiceTest {

    @Mock
    private Client client;

    private RoutingService routingService;


    @BeforeEach
    void setup() {
        routingService = new RoutingServiceImpl(client);
    }

    @Test
    void routingTest() {
        assert(true);
    }
}
