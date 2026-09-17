package com.telai.api.agent.shared.domain.tool.strategy;

import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public record HttpRequestToolEntry(
        HttpMethod method,
        Map<String, String> headers,
        Map<String, ?> parameters
) {

    public static HttpRequestToolEntry fromArguments(String arguments) {
        var mapper = new ObjectMapper();

        return mapper.readValue(arguments, HttpRequestToolEntry.class);
    }
}
