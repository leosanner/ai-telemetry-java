package com.telai.api.agent.shared.domain.tool;

import com.telai.api.agent.shared.domain.tool.strategy.ToolExecutionStrategy;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.service.tool.AiServiceTool;

import java.util.List;

public class ToolFactory {

    public AiServiceTool create(
            ToolConfig config,
            ToolExecutionStrategy executionStrategy
    ) {
        ToolSpecification toolSpecification = getToolSpecification(config);
        var executor = ToolExecuterFactory.create(executionStrategy);

        return AiServiceTool
                .builder()
                .toolSpecification(toolSpecification)
                .toolExecutor(executor)
                .build();
    }

    private ToolSpecification getToolSpecification(ToolConfig config) {
        return ToolSpecification
                .builder()
                .name(config.name())
                .description(config.description())
                .parameters(createSchema(config.parameters()))
        .build();
    }

    private JsonObjectSchema createSchema(List<ToolParameters> toolParameters) {
        var builder = JsonObjectSchema.builder();

        for (var toolParameter : toolParameters) {
            var name = toolParameter.name();
            var description = toolParameter.description();

            switch (toolParameter.parameterType()) {
                case STRING -> builder.addStringProperty(name, description);
                case BOOLEAN -> builder.addBooleanProperty(name, description);
                case INTEGER -> builder.addIntegerProperty(name, description);
            }
        }

        return builder.build();
    }

}
