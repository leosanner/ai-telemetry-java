package com.telai.api.agent.shared.domain.tool;

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.service.tool.AiServiceTool;
import dev.langchain4j.service.tool.ToolExecutor;

import java.util.List;

public class ToolFactory {

    public AiServiceTool create(
            ToolConfig config,
            ToolExecutor executor
    ) {
        ToolSpecification toolSpecification = getToolSpecification(config);

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
                case STRING:
                    builder.addStringProperty(name, description);

                case BOOLEAN:
                    builder.addBooleanProperty(name, description);

                case INTEGER:
                    builder.addBooleanProperty(name, description);
            }
        }

        return builder.build();
    }

}
