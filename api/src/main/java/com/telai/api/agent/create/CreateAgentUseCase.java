package com.telai.api.agent.create;

import com.telai.api.agent.shared.domain.Agent;
import com.telai.api.agent.shared.domain.AgentConfig;
import com.telai.api.agent.shared.domain.AgentFactory;
import com.telai.api.agent.shared.domain.LangChainRuntimeFactory;
import com.telai.api.agent.shared.domain.tool.ToolFactory;

public class CreateAgentUseCase {

    private AgentFactory agentFactory;

    public CreateAgentUseCase(AgentFactory agentFactory) {
        this.agentFactory = agentFactory;
    }

    public Agent execute(AgentConfig config) {
        return this.agentFactory.fromConfig(config);
    }
}
