package com.eazybytes.openai.controller;

import com.eazybytes.openai.model.CountryCity;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructuredOutputController {
    private final ChatClient client;//chat with LLM model
    public StructuredOutputController(ChatClient.Builder chatClientBuilder){
        this.client=chatClientBuilder.build();
    }
    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCity> chatBean(@RequestParam("message")String message){
        CountryCity countryCity = client.prompt()
                .user(message).call().entity(new BeanOutputConverter<>(CountryCity.class));
//                .entity(CountryCity.class);
        return ResponseEntity.ok(countryCity);
    }
    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message")String message){
        List<String> countryCities = client.prompt()
                .user(message).call().entity(new ListOutputConverter());
        return ResponseEntity.ok(countryCities);
    }
    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<CountryCity>> chatBeanList(@RequestParam("message")String message){
        List<CountryCity> countryCities = client.prompt()
                .user(message).call().entity(new ParameterizedTypeReference<List<CountryCity>>() {});
        return ResponseEntity.ok(countryCities);
    }
    @GetMapping("/chat-map")
    public ResponseEntity<Map<String,Object>> chatMap(@RequestParam("message")String message){
        Map<String,Object> countryCities = client.prompt()
                .user(message).call().entity(new MapOutputConverter());
        return ResponseEntity.ok(countryCities);
    }
}
