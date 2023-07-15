package com.cta4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import com.cta4j.client.TrainClient;
import org.springframework.beans.factory.annotation.Value;
import java.util.Objects;
import org.eclipse.jetty.client.HttpClient;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.JettyClientHttpConnector;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public TrainClient trainClient(@Value("${cta.train-api-key}") String apiKey) {
        Objects.requireNonNull(apiKey);

        String baseUrl = """
        https://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=%s&outputType=json""".formatted(apiKey);

        HttpClient httpClient = new HttpClient();

        ClientHttpConnector clientHttpConnector = new JettyClientHttpConnector(httpClient);

        WebClient webClient = WebClient.builder()
                                       .baseUrl(baseUrl)
                                       .clientConnector(clientHttpConnector)
                                       .build();

        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClient);

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(webClientAdapter)
                                                                                 .build();

        return httpServiceProxyFactory.createClient(TrainClient.class);
    }
}
