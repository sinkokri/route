package cz.sinkokri.routing.client;

import cz.sinkokri.routing.dto.Country;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Client {

    private final RestTemplate restTemplate;

    @Value("${route.url}")
    private String routeUrl;

    @SneakyThrows
    private <T> ResponseEntity<T> sendRequest(String url, ParameterizedTypeReference<T> parameterizedTypeReference) {
        log.info("Sending http request to : {}", url);
        var headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        var entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url,
                HttpMethod.GET,
                entity,
                parameterizedTypeReference);
    }

    public List<Country> fetchCountries() {
        return sendRequest(routeUrl, new ParameterizedTypeReference<List<Country>>(){}).getBody();
    }


}
