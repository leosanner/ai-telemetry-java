package com.telai.api.agent.shared.domain;

import com.telai.api.agent.shared.domain.tool.Tool;
import com.telai.api.agent.shared.domain.tool.ToolFactory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.AiServiceTool;

import java.util.ArrayList;
import java.util.List;

public class LangChainRuntimeFactory {

    private final ToolFactory toolFactory;
    private final ModelFactory modelFactory;

    public LangChainRuntimeFactory(
            ToolFactory toolFactory,
            ModelFactory modelFactory
    ) {
        this.toolFactory = toolFactory;
        this.modelFactory = modelFactory;
    }

    public RuntimeAgent create(Agent agent) {
        ChatModel chatModel = createChatModel(agent);

        return AiServices
                .builder(RuntimeAgent.class)
                .chatModel(chatModel)
                .tools(createTools(agent.tools()))
                .build();
    }

    private ChatModel createChatModel(Agent agent) {
        ChatModel chatModel = this.modelFactory.createChatModel(agent.model());
        return chatModel;
    }

    private List<AiServiceTool> createTools(List<Tool> tools) {
        if (tools == null) {
            return null;
        }

        List<AiServiceTool> toolList = new ArrayList<>();

        for (Tool tool : tools) {
            toolList.add(toolFactory.create(tool));
        }

        return toolList;
    }
}
