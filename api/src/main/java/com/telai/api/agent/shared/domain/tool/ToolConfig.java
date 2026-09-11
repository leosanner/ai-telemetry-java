package com.telai.api.agent.shared.domain.tool;

import com.telai.api.kernel.error.exceptions.InvariantError;

import java.util.List;

public record ToolConfig(
        String name,
        String description,
        List<ToolParameters> parameters
) {

    public ToolConfig {
        if (name == null) {
            throw new InvariantError("name cannot be null");
        }

        if (description == null) {
            throw new InvariantError("description cannot be null");
        }

        if (parameters.isEmpty()) {
            throw new InvariantError("parameters cannot be empty");
        }
    }
}
