package com.telai.api.agent.shared.domain.tool;

import dev.langchain4j.agent.tool.ToolExecutionRequest;

public interface ToolExecutionStrategy {
    String execute(ToolExecutionRequest request);
}
