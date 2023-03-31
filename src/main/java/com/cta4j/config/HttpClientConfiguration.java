package com.cta4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.cta4j.client.StationClient;
import org.springframework.beans.factory.annotation.Value;
import java.util.Objects;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public StationClient trainClient(@Value("${TRAIN_API_KEY}") String apiKey) {
        Objects.requireNonNull(apiKey);

        String baseUrl = """
        https://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=%s&outputType=json""".formatted(apiKey);

        WebClient webClient = WebClient.create(baseUrl);

        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(webClientAdapter)
                                                                                 .build();

        return httpServiceProxyFactory.createClient(StationClient.class);
    }
}
