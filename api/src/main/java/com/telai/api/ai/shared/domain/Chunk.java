package com.telai.api.ai.shared.domain;

import java.util.List;
import java.util.Map;

public record Chunk(
        String id,
        Integer position,
        String documentId,
        List<Double> embedding,
        Map<String, ?> metadata
) {
}
