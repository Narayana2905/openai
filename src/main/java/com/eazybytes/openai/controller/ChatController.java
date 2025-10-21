package com.eazybytes.openai.controller;

import com.eazybytes.openai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatClient client;//chat with LLM model
    public ChatController(ChatClient chatClient){
        this.client=chatClient;
    }
    @GetMapping("/chat")
    public String chat(@RequestParam("message")String message){
        return client.prompt()
//                .advisors(new TokenUsageAuditAdvisor())
                .user(message).call().content();
    }
}

