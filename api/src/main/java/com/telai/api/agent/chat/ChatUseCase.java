package com.telai.api.agent.chat;

import com.telai.api.agent.shared.domain.AgentResponse;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface ChatUseCase {
    @UserMessage("""
        Question: {{prompt}}
        Language: {{language}}
    """)
    Result<AgentResponse> execute(
            @V("prompt") String prompt,
            @V("language") String language
    );
}
