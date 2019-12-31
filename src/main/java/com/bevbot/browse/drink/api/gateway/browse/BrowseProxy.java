package com.bevbot.browse.drink.api.gateway.browse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class BrowseProxy {
    private Logger logger = LoggerFactory.getLogger(BrowseProxy.class);

    @Value("${bartender.api.graph.ql.url}")
    private URI bartenderApiGraphQlUrl;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/browse/api/restaurants.json")
    public String browseRestaurants() {
        String getRestaurantsQuery = asString(resourceLoader.getResource("classpath:/graphql/browse/getRestaurants.graphql"));

        GraphQLQuery graphQlQuery = new GraphQLQuery();
        graphQlQuery.setQuery(getRestaurantsQuery);

        GraphQLQueryResponse response = restTemplate.postForObject(bartenderApiGraphQlUrl.toString(), graphQlQuery, GraphQLQueryResponse.class);
        logger.info("graphQl response: {}", response);
        String restaurants = response.getData().get("getRestaurants").toString();
        logger.info("restaurants: {}", restaurants);
        return restaurants;
    }

    public String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
