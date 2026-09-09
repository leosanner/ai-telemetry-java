package com.telai.api.agent.shared.domain;

public record Agent(
        String id,
        Provider provider
) {

    public Agent {
        if (id == null) {
            throw new NullPointerException("id is null");
        }

        if (provider == null) {
            throw new NullPointerException("provider is null");
        }
    }
}
