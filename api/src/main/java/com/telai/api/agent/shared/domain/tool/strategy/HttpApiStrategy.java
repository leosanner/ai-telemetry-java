package com.telai.api.agent.shared.domain.tool.strategy;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpApiStrategy implements ToolExecutionStrategy {

    private final HttpClient client =  HttpClient.newHttpClient();

    public String execute(
            ToolExecutionRequest request,
                          Object memoryId
    ) throws IOException, InterruptedException {

        var arguments = HttpRequestToolEntry.fromArguments(request.arguments());
        var clientRequest = buildRequest(arguments);

        HttpResponse<String> response = client.send(
                clientRequest,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.toString();
    }

    private HttpRequest buildRequest(HttpRequestToolEntry arguments) {
        var builder = HttpRequest.newBuilder();
        var mapper = new ObjectMapper();

        builder.method(
                arguments.method().name(),
                HttpRequest.BodyPublishers.ofString(
                        mapper.writeValueAsString(arguments)
                )
        );

        arguments.headers().forEach((key, value) -> builder.header(key, value));

        return builder.build();
    }
}
