package com.telai.api.agent.shared.domain;

import com.telai.api.agent.shared.domain.tool.Tool;

import java.util.List;

public record AgentConfig(
        Model model,
        List<Tool> tools
) {
}
