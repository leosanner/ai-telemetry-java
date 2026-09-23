package com.telai.api.agent.shared.domain;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class ModelFactory {

    public ChatModel createChatModel(Model model) {
        return switch (model.provider()) {
            case OLLAMA -> OllamaChatModel
                    .builder()
                    .modelName(model.name())
                    .build();
        };
    }
}
