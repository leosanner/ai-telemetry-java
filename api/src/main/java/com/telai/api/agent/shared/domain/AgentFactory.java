package com.telai.api.agent.shared.domain;

import com.telai.api.agent.shared.domain.tool.ToolFactory;
import com.telai.api.kernel.gateway.IDGenerator;

public class AgentFactory {

    private final IDGenerator idGenerator;
    private final ToolFactory toolFactory;

    public AgentFactory(
            IDGenerator idGenerator,
            ToolFactory toolFactory)
    {
        this.idGenerator = idGenerator;
        this.toolFactory = toolFactory;
    }

    public Agent fromConfig(AgentConfig config) {

        return new Agent(
                idGenerator.generate(),
                config.model(),
                config.tools()
        );
    }
}
