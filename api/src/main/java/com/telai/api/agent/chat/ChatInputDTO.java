package com.telai.api.agent.chat;

import com.telai.api.agent.shared.domain.Language;

public record ChatInputDTO(
        String prompt,
        Language language
) {
    public static ChatInputDTO from(ChatRequestDTO dto) {
        return new ChatInputDTO(
                dto.prompt(),
                Language.fromString(dto.language())
        );
    }
}
