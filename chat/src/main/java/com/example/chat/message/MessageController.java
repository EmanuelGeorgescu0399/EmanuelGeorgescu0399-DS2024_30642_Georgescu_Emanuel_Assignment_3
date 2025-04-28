package com.example.chat.message;

import com.example.chat.dto.ChatDTO;
import com.example.chat.dto.TypingDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/chat")
@CrossOrigin
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatDTO chatDTO) {
        if (Objects.equals(chatDTO.getReceiver(), "admin")) {
            // Client -> Admin
            messagingTemplate.convertAndSend("/topic/chat/admin/" + chatDTO.getSender(), chatDTO);
        } else {
            // Admin -> Client
            messagingTemplate.convertAndSend("/topic/chat/" + chatDTO.getReceiver(), chatDTO);
        }
    }

    @MessageMapping("/sendReadMessage")
    public void sendReadMessage(ChatDTO chatDTO) {
        chatDTO.setSeen(true);

        if (Objects.equals(chatDTO.getSender(), "admin")) {
            // Adminul a trimis mesajul, Clientul l-a citit
            messagingTemplate.convertAndSend("/topic/chat/admin/" + chatDTO.getReceiver(), chatDTO);
            messagingTemplate.convertAndSend("/topic/chat/" + chatDTO.getReceiver(), chatDTO);
        } else {
            // Clientul a trimis mesajul, Adminul l-a citit
            messagingTemplate.convertAndSend("/topic/chat/admin/" + chatDTO.getSender(), chatDTO);
            messagingTemplate.convertAndSend("/topic/chat/" + chatDTO.getSender(), chatDTO);
        }
    }



    @MessageMapping("/sendTypingMessage")
    public void sendTypingMessage(TypingDTO typingDTO) {
        if (Objects.equals(typingDTO.getReceiver(), "admin")) {
            messagingTemplate.convertAndSend("/topic/typing/admin/" + typingDTO.getSender(), typingDTO.getTyping());
        } else {
            messagingTemplate.convertAndSend("/topic/typing/" + typingDTO.getReceiver(), typingDTO.getTyping());
        }
    }
}
