package com.spring_ai.example.service.impl;

import com.spring_ai.example.model.Answer;
import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.OpenAIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String getAnswer(String question) {
        return this.chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

    @Override
    public Answer getAnswer(Question question) {
        String response = this.chatClient.prompt()
                .user(question.question())
                .call()
                .content();
        return new Answer(response);
    }
}
