package com.dongguk.ossdev.backend.domain;

import lombok.Builder;

public class ChatGptMessage {
    private String role;
    private String content;

    @Builder
    public ChatGptMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
