package com.telai.api.agent.shared.domain.tool.strategy;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ToolStrategies {
    HTTP_REQUEST("http", new HttpApiStrategy());

    private final String name;
    private final ToolExecutionStrategy executionStrategy;

    private static final Map<String, ToolStrategies> BY_KEY =
            Arrays
                    .stream(ToolStrategies.values())
                    .collect(Collectors.toUnmodifiableMap(
                            ToolStrategies::getName,
                            Function.identity()
                    ));

    ToolStrategies(String name, ToolExecutionStrategy executionStrategy) {
        this.name = name;
        this.executionStrategy = executionStrategy;
    }

    public String getName() {
        return name;
    }

    public ToolExecutionStrategy getExecutionStrategy() {
        return executionStrategy;
    }

    public static Optional<ToolStrategies> getByKey(String key) {
        if (key == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(BY_KEY.get(key.toLowerCase(Locale.ROOT)));
    }
}
