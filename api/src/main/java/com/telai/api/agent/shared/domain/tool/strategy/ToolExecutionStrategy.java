package com.telai.api.agent.shared.domain.tool.strategy;

import dev.langchain4j.agent.tool.ToolExecutionRequest;

import java.io.IOException;

public interface ToolExecutionStrategy {
    // Ajustar para try catch interno
    String execute(ToolExecutionRequest request);
}
