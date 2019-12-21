package com.bevbot.browse.drink.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
public class BrowseDrinkApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrowseDrinkApiGatewayApplication.class, args);
    }

    @Bean
    public SecurityWebFilterChain staticContentSpringSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .anyExchange()
                .permitAll();
        return http.build();
    }

}
