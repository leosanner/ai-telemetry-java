package com.telai.api.agent.shared.domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Provider {
    OLLAMA("ollama");

    private final String name;

    private static final Map<String, Provider> BY_KEY =
            Arrays
                    .stream(Provider.values())
                            .collect(Collectors.toUnmodifiableMap(
                                    Provider::getName,
                                    Function.identity()
                            ));

    Provider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<Provider> getByKey(String key) {
        if (key == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(BY_KEY.get(key.toLowerCase(Locale.ROOT)));
    }
}
