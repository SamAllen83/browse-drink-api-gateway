package com.bevbot.browse.drink.api.gateway.browse;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class BrowseProxy {
    private Logger logger = LoggerFactory.getLogger(BrowseProxy.class);

    @Value("${bartender.api.graph.ql.url}")
    private URI bartenderApiGraphQlUrl;

    @GetMapping("/browse")
    public Mono<String> browse(ProxyExchange<byte[]> proxy) {
        String orderDrinkMutation ="mutation {\n" + "    orderDrink(customerName: \"Sam\", drinkName: \"Beer\") {\n" + "        customerName\n" + "        drinkName\n"
                + "        orderId\n" + "    }\n" + "}";

        GraphQLQuery topologyQuery = new GraphQLQuery();
        topologyQuery.setQuery(orderDrinkMutation);

        logger.info("Starting NON-BLOCKING Controller!");
        Mono<String> orderDrinkResponse = WebClient.create(bartenderApiGraphQlUrl.toString())
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(topologyQuery))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        orderDrinkResponse.subscribe(tweet -> logger.info(orderDrinkResponse.toString()));
        logger.info("Exiting NON-BLOCKING Controller!");
        return orderDrinkResponse;

    }
}
