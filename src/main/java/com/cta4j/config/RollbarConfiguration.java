package com.cta4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.rollbar.notifier.Rollbar;
import org.springframework.beans.factory.annotation.Value;
import java.util.Objects;
import com.rollbar.notifier.config.Config;
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder;

@Configuration
public class RollbarConfiguration {
    @Bean
    public Rollbar rollbar(@Value("${rollbar.access-token}") String accessToken,
        @Value("${rollbar.environment}") String environment) {
        Objects.requireNonNull(accessToken);

        Objects.requireNonNull(environment);

        Config config = RollbarSpringConfigBuilder.withAccessToken(accessToken)
                                                  .environment(environment)
                                                  .build();

        return new Rollbar(config);
    }
}
