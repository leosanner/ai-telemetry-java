package com.telai.api.agent.create;

import com.telai.api.agent.shared.domain.AgentConfig;
import com.telai.api.agent.shared.domain.Model;
import com.telai.api.agent.shared.domain.Provider;
import com.telai.api.kernel.error.exceptions.InvariantError;

import java.util.List;

public record CreateAgentRequest(
        String modelProvider,
        String modelName,
        List<ToolRequest> tools
) {
    public AgentConfig toConfig() {
        var provider = Provider.getByKey(this.modelProvider).orElseThrow(
                () -> new InvariantError("provider not found")
        );

        var tools = this.tools.stream()
                .map(ToolRequest::toTool)
                .toList();

        return new AgentConfig(new Model(provider, this.modelName), tools);
    }
}
