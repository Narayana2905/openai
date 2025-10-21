package com.eazybytes.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {
    private final ChatClient client;
    public PromptStuffingController(ChatClient chatClient){
        this.client=chatClient;
    }
    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;
    @GetMapping("/prompt-stuffing")
    public String promptStuffing(@RequestParam("message")String message){
        return client.prompt()
                .options(OllamaOptions.builder().model(OllamaModel.LLAMA3_2_1B).build())
                .system(systemPromptTemplate)
                .user(message)
                .call()
                .content();
    }
}