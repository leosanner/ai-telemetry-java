package com.telai.api.ai.shared.domain;

import java.util.Map;

public record Document(
        String id,
        Map<String, ?> metadata,
        String ownerId
) {
}
