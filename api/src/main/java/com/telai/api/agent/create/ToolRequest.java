package com.telai.api.agent.create;

import com.telai.api.agent.shared.domain.tool.Tool;
import com.telai.api.agent.shared.domain.tool.ToolParameters;
import com.telai.api.agent.shared.domain.tool.strategy.ToolStrategies;
import com.telai.api.kernel.error.exceptions.InvariantError;

import java.util.List;

public record ToolRequest(
        String name,
        String description,
        List<ToolParameters> parameters,
        String strategy
) {
    public Tool toTool() {
        var executionStrategy = ToolStrategies.getByKey(this.strategy)
                .map(ToolStrategies::getExecutionStrategy)
                .orElseThrow(() -> new InvariantError("strategy not found"));

        return new Tool(name, description, parameters, executionStrategy);
    }
}
