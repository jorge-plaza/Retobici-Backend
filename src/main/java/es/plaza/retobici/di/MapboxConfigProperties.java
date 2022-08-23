package es.plaza.retobici.di;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mapbox")
public record MapboxConfigProperties(String  apiUrl, String apiVersion, String authToken){}
