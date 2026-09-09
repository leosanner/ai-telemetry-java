package com.telai.api.agent.chat.http;

import com.telai.api.agent.shared.domain.AgentResponse;
import dev.langchain4j.service.Result;

public record ChatResponse(
        AgentResponse response
) {
    public static ChatResponse from(
            Result<AgentResponse> result
    ) {
        return new ChatResponse(
                result.content()
        );
    }
}
