package com.telai.api.agent.create;

import com.telai.api.agent.shared.domain.Agent;
import com.telai.api.agent.shared.domain.tool.ToolFactory;

public class CreateAgentUseCase {

    private final ToolFactory toolFactory;

    public CreateAgentUseCase(ToolFactory toolFactory) {
        this.toolFactory = toolFactory;
    }

    public Agent execute() {
        return null;
    }
}
