package com.telai.api.agent.shared.domain.tool;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ParameterType {
    STRING("string", String.class),
    INTEGER("integer", Integer.class),
    BOOLEAN("boolean",  Boolean.class),;

    private final String value;
    private final Class<?> clazz;


    ParameterType(String value,  Class<?> clazz) {
        this.value = value;
        this.clazz = clazz;
    }

    private String getValue() {
        return value;
    }

    private static final Map<String, ParameterType> BY_KEY =
            Arrays
                    .stream(ParameterType.values())
                    .collect(
                            Collectors.toUnmodifiableMap(
                                    ParameterType::getValue,
                                    Function.identity()
                            )
                    );

    public static Optional<ParameterType> fromString(String key) {
        if (key == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(BY_KEY.get(key.toLowerCase(Locale.ROOT)));
    }

    public boolean matches(Class<?> clazz) {
        return this.clazz == clazz;
    }
}
