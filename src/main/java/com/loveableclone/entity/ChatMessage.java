package com.loveableclone.entity;

import com.loveableclone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;
    ChatSession chatSession;

    MessageRole messageRole;

    String content;
    String toolCalls;//It is Json array of tools called

    Integer tokenUsed;

    Instant createdAt;


}
