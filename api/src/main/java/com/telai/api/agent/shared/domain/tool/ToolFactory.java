package com.telai.api.agent.shared.domain.tool;

import com.telai.api.agent.shared.domain.tool.strategy.ToolStrategies;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.service.tool.AiServiceTool;

import java.util.ArrayList;
import java.util.List;

public class ToolFactory {

    public AiServiceTool create(Tool toolConfig) {
        ToolSpecification toolSpecification = getToolSpecification(toolConfig);
        var executor = ToolExecuterFactory.create(toolConfig.strategy());

        return AiServiceTool
                .builder()
                .toolSpecification(toolSpecification)
                .toolExecutor(executor)
                .build();
    }

    private ToolSpecification getToolSpecification(Tool config) {
        return ToolSpecification
                .builder()
                .name(config.name())
                .description(config.description())
                .parameters(createSchema(config.parameters()))
        .build();
    }

    private JsonObjectSchema createSchema(List<ToolParameters> toolParameters) {
        var builder = JsonObjectSchema.builder();
        var requiredParameters = new ArrayList<String>();

        for (var toolParameter : toolParameters) {
            var name = toolParameter.name();
            var description = toolParameter.description();

            if (toolParameter.required()) {requiredParameters.add(name);}

            switch (toolParameter.parameterType()) {
                case STRING -> builder.addStringProperty(name, description);
                case BOOLEAN -> builder.addBooleanProperty(name, description);
                case INTEGER -> builder.addIntegerProperty(name, description);
            }
        }

        builder.required(requiredParameters);
        return builder.build();
    }

}
