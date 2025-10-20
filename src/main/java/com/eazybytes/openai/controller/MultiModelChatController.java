package com.eazybytes.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MultiModelChatController {
    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;
    public MultiModelChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient,@Qualifier("openAiChatClient") ChatClient openAiChatClient){
        this.ollamaChatClient=ollamaChatClient;
        this.openAiChatClient=openAiChatClient;
    }
    @GetMapping("/openapi/chat")
    public String openAiChat(@RequestParam("message") String message){
        return openAiChatClient.prompt(message).call().content();
    }
    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam("message") String message){
        return ollamaChatClient.prompt(message).call().content();
    }
}
