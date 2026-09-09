package com.telai.api.agent.chat.http;

import com.telai.api.agent.chat.ChatRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ChatRequest(
        @NotEmpty @Size(min = 1, max = 200) String prompt,
        String language
) {
    public ChatRequestDTO toDTO() {
        return new ChatRequestDTO(prompt, language);
    }
}
