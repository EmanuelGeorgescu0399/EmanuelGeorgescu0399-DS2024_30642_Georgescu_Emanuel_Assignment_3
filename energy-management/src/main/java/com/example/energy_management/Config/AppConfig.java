package com.example.energy_management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Configurare bean RestTemplate pentru gestionarea apelurilor HTTP externe
    @Bean
    public RestTemplate configureRestTemplate() {
        return new RestTemplate();
    }
}