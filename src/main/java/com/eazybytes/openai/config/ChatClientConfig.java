package com.eazybytes.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.SimpleApiKey;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
//    @Bean
//    public OpenAiChatModel openAiChatModel() {
//        return new OpenAiChatModel(OpenAiApi.builder()
//                .baseUrl("http://localhost:12434")
//                .apiKey("dummyKey")
//                .build());
//    }
//
//    @Bean
//    public OllamaChatModel ollamaChatModel() {
//        return new OllamaChatModel(OllamaApi.builder()
//                .baseUrl("http://localhost:11434") // default Ollama port
//                .model("llama3.2:1b")
//                .build());
//    }
    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel){
        return  ChatClient.create(openAiChatModel);
    }
    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel){
//        return ChatClient.create(ollamaChatModel);
        ChatClient.Builder chatClientBuilder=ChatClient.builder(ollamaChatModel);
        return chatClientBuilder.build();
    }
}
