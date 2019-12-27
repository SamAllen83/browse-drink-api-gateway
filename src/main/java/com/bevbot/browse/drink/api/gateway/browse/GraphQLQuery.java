package com.bevbot.browse.drink.api.gateway.browse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphQLQuery {
    @JsonProperty("variables")
    private Object variables;

    @JsonProperty("query")
    private String query;

    public GraphQLQuery() {
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
