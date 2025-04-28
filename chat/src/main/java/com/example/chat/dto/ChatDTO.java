package com.example.chat.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private String messageId;
    private String sender;
    private String receiver;
    private String message;
    private Boolean seen;
}
