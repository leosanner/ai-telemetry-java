package com.telai.api.agent.shared.domain;

public enum Provider {
    OLLAMA("ollama");

    private String name;

    Provider(String name) {
        this.name = name;
    }
}
