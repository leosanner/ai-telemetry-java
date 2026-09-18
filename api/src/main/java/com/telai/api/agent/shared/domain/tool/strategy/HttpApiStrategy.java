package com.telai.api.agent.shared.domain.tool.strategy;

import com.telai.api.kernel.error.exceptions.ToolError;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpApiStrategy implements ToolExecutionStrategy {

    private final HttpClient client =  HttpClient.newHttpClient();

    public String execute(ToolExecutionRequest request) {

        var arguments = HttpRequestToolEntry.fromArguments(request.arguments());
        var clientRequest = buildRequest(arguments);

        return sendRequest(clientRequest);
    }

    private String sendRequest(HttpRequest clientRequest) {
        try {
            HttpResponse<String> response = client.send(
                    clientRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            return response.toString();

        } catch (IOException e) {
            throw new ToolError("Failed to call external service", e);
        } catch (InterruptedException e) {
            throw new ToolError("Request was interrupted", e);
        }
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
