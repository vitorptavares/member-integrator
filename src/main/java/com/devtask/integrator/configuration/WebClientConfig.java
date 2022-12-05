package com.devtask.integrator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientConfig {
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
