package com.telai.api.agent.shared.domain;

import com.telai.api.agent.shared.domain.tool.ToolFactory;

public class AgentFactory {

    private final ToolFactory toolFactory;

    public AgentFactory(ToolFactory toolFactory) {
        this.toolFactory = toolFactory;
    }

}
