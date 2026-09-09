package com.telai.api.agent.chat.http;

import dev.langchain4j.service.tool.ToolExecution;

import java.util.List;

public record ToolCalled(
        String name,
        String arguments,
        String result
) {

    public static ToolCalled fromToolExecution(ToolExecution toolExecution) {
        return new ToolCalled(
                toolExecution.request().name(),
                toolExecution.request().arguments(),
                toolExecution.result()
        );
    }
}
