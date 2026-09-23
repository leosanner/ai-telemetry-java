package com.telai.api.agent.shared.domain;

import com.telai.api.agent.shared.domain.tool.Tool;

import java.util.List;

public record Agent(
        String id,
        Model model,
        List<Tool> tools
) {

    public Agent {
        if (id == null) {
            throw new NullPointerException("id is null");
        }

        if (model == null) {
            throw new NullPointerException("provider is null");
        }
    }
}
