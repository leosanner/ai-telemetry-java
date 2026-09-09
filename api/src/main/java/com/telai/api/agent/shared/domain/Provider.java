package com.telai.api.agent.shared.domain;

public enum Provider {
    OLLAMA("ollama");

    private final String name;

    Provider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
