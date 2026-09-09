package com.telai.api.agent.chat.http;

import com.telai.api.agent.shared.domain.AgentResponse;
import dev.langchain4j.service.Result;

import java.util.List;

public record ChatResponse(
        List<ToolCalled> tools,
        AgentResponse response
) {
    public static ChatResponse from(
            Result<AgentResponse> result
    ) {
        return new ChatResponse(
                result.toolExecutions()
                        .stream()
                        .map(ToolCalled::fromToolExecution)
                        .toList(),
                result.content()
        );
    }
}
