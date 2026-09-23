package com.telai.api.agent.shared.domain.tool;

import com.telai.api.agent.shared.domain.tool.strategy.ToolExecutionStrategy;
import com.telai.api.kernel.error.exceptions.InvariantError;

import java.util.List;

public record Tool(
        String name,
        String description,
        List<ToolParameters> parameters,
        ToolExecutionStrategy strategy
) {

    public Tool {
        if (name == null) {
            throw new InvariantError("name cannot be null");
        }

        if (description == null) {
            throw new InvariantError("description cannot be null");
        }

        if (parameters == null) {
            throw new InvariantError("parameters cannot be null");
        }

        parameters = List.copyOf(parameters);
    }
}
