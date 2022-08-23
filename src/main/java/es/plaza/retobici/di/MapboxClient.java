package es.plaza.retobici.di;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MapboxClient {
    private final MapboxConfigProperties mapboxConfigProperties;
    public final WebClient client;

    public final String token;

    public MapboxClient(MapboxConfigProperties mapboxConfigProperties) {
        this.mapboxConfigProperties = mapboxConfigProperties;
        this.token = mapboxConfigProperties.authToken();
        this.client = WebClient.create();
    }
}
