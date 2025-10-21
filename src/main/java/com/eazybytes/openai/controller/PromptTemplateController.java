package com.eazybytes.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {
    private final ChatClient client;
    public PromptTemplateController(ChatClient chatClient){
        this.client=chatClient;
    }
    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;
//    String promptTemplate =
//            """
//                A customer named {customerName} sent the following message:
//                               "{customerMessage}"
//
//                Write a polite and helpful email response addressing the issue.
//                Maintain a professional tone and provide reassurance.
//
//                Respond as if you're writing the email body only. Don't include subject,
//                signature
//            """;
    @GetMapping("/email")
    public String chatResponse(@RequestParam("customerName")String customerName,
                       @RequestParam("customerMessage")String customerMessage){
        return client.prompt()
                .system("""
                        You are a professional customer service assistant which helps drafting email
                        responses to improve the productivity of the customer support team
                        """)
//                .user(promptUserSpec -> promptUserSpec.text(promptTemplate)
//                        .param("customerName",customerName)
//                        .param("customerMessage",customerMessage))
                .user(promptUserSpec -> promptUserSpec.text(userPromptTemplate)
                        .param("customerName",customerName)
                        .param("customerMessage",customerMessage))
                .call().content();
    }
}