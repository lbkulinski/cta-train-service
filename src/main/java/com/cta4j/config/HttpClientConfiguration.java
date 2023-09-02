package com.cta4j.config;

import com.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Objects;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public TrainClient trainClient(@Value("${cta.train-api-key}") String apiKey) {
        Objects.requireNonNull(apiKey);

        String baseUrl = """
        https://lapi.transitchicago.com/api/1.0?key=%s&outputType=json""".formatted(apiKey);

        WebClient webClient = WebClient.builder()
                                       .baseUrl(baseUrl)
                                       .build();

        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(webClientAdapter)
                                                                                 .build();

        return httpServiceProxyFactory.createClient(TrainClient.class);
    }
}
