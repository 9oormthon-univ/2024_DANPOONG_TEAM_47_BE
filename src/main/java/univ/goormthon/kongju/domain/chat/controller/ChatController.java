package univ.goormthon.kongju.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    @MessageMapping("/chat")
    public void sendMessage(String message) {
        this.template.convertAndSend("/sub", message);
    }

}
