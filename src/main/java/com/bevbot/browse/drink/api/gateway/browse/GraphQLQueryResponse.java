package com.bevbot.browse.drink.api.gateway.browse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class GraphQLQueryResponse {

    @JsonProperty("data")
    private JsonNode data;

    public GraphQLQueryResponse() {
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GraphQLQueryResponse{");
        if(data!=null) {
            sb.append("data=").append(data.toString());
        }
        sb.append('}');
        return sb.toString();
    }
}
