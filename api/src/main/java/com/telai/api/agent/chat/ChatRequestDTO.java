package com.telai.api.agent.chat;


import com.telai.api.agent.chat.http.ChatRequest;

public record ChatRequestDTO(
        String prompt,
        String language
) {
    public static ChatRequestDTO from(
           ChatRequest chatRequest
    ) {
        return new ChatRequestDTO(
                chatRequest.prompt(),
                chatRequest.language()
        );
    }
}
