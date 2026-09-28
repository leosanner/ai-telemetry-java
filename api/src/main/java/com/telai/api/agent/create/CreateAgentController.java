package com.telai.api.agent.create;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create")
public class CreateAgentController {

    private CreateAgentUseCase createAgentUseCase;

    public CreateAgentController(CreateAgentUseCase createAgentUseCase) {
        this.createAgentUseCase = createAgentUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<CreateAgentResponse> createAgent(
            @Valid @RequestBody CreateAgentRequest createAgentRequest
    ) {
        var agentConfig = createAgentRequest.toConfig();
        var agentCreated = this.createAgentUseCase.execute(agentConfig);

        return ResponseEntity.ok(new CreateAgentResponse(agentCreated));
    }
}
