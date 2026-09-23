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

    public CreateAgentController() {

    }

    @PostMapping("/")
    public ResponseEntity<CreateAgentResponse> createAgent(
            @Valid @RequestBody CreateAgentRequest createAgentRequest
    ) {

    }
}
