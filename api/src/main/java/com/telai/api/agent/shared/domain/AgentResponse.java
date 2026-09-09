package com.telai.api.agent.shared.domain;

public record AgentResponse(
        String response
) {
    public AgentResponse {
        if (response == null) {
            throw new NullPointerException("response is null");
        }
    }
}
