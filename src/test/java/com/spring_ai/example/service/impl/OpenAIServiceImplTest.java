package com.spring_ai.example.service.impl;

import com.spring_ai.example.service.OpenAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void getAnswer() {
        String answer = openAIService.getAnswer("Tell me a joke");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}