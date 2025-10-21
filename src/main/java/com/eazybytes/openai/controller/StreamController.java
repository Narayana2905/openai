package com.eazybytes.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class StreamController {
    private final ChatClient client;
    public StreamController(ChatClient chatClient){
        this.client=chatClient;
    }
    @GetMapping("/stream")
    public Flux<String> streamController(@RequestParam("message")String message){
        return client.prompt()
                .user(message)
                .stream()
                .content();
    }
}