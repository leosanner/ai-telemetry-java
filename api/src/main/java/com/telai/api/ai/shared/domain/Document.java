package com.telai.api.ai.shared.domain;

import com.telai.api.kernel.domain.refs.UserReference;

import java.util.Map;

public record Document(
        String id,
        Map<String, ?> metadata,
        UserReference ownerId
) {
}
