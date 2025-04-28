package com.example.chat.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypingDTO {
    private String messageId;
    private String sender;
    private String receiver;
    private Boolean typing;
}
