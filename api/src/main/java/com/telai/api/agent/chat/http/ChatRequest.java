package com.telai.api.agent.chat.http;

import com.telai.api.agent.chat.ChatRequestDTO;

public record ChatRequest(
        String prompt,
        String language
) {
    public ChatRequestDTO toDTO() {
        return new ChatRequestDTO(prompt, language);
    }
}
