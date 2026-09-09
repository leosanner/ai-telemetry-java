package com.telai.api.agent.chat.http;

import com.telai.api.agent.chat.ChatUseCase;
import com.telai.api.agent.chat.ChatInputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatUseCase chatUseCase;

    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @GetMapping("/")
    public ResponseEntity<ChatResponse> chat(ChatRequest chatRequest) {
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
