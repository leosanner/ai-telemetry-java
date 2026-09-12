package com.telai.api.agent.chat.http;

import com.telai.api.agent.chat.ChatUseCase;
import com.telai.api.agent.chat.ChatInputDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatUseCase chatUseCase;

    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<ChatResponse> chat(
            @RequestBody @Valid ChatRequest chatRequest) {
        var chatInputDTO = ChatInputDTO.from(chatRequest.toDTO());
        var chatResponse = ChatResponse.from(
                this.chatUseCase.execute(
                        chatInputDTO.prompt(),
                        chatInputDTO.language().toString()
                )
        );

        return new ResponseEntity<>(chatResponse, HttpStatus.OK);
    }
}
