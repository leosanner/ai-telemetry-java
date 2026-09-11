package com.telai.api.agent.shared.domain.tool;

public record ToolParameters(
        String name,
        ParameterType parameterType,
        String description
) {
}
