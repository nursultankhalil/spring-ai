package com.spring_ai.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_ai.example.model.Answer;
import com.spring_ai.example.model.CapitalRequest;
import com.spring_ai.example.model.CapitalResponse;
import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.OpenAIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource capitalResource;

    @Value("classpath:templates/get-capital-more-info-prompt.st")
    Resource capitalMoreResource;

    @Value("classpath:templates/get-capital-json-prompt.st")
    Resource capitalJsonResource;

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

    @Override
    public Answer getAnswer(CapitalRequest capital) {
        PromptTemplate promptTemplate = new PromptTemplate(capitalResource);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capital.stateOrCountry()));
        String response = chatClient.prompt(prompt)
                .call()
                .content();

        return new Answer(response);
    }

    @Override
    public Answer getAnswerMoreInfo(CapitalRequest capital) {
        PromptTemplate promptTemplate = new PromptTemplate(capitalMoreResource);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capital.stateOrCountry()));
        String response = chatClient.prompt(prompt)
                .call()
                .content();

        return new Answer(response);
    }

    @Override
    public CapitalResponse getAnswerJson(CapitalRequest capital) {
        BeanOutputConverter<CapitalResponse> converter = new BeanOutputConverter<>(CapitalResponse.class);
        String format = converter.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(capitalJsonResource);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capital.stateOrCountry(),
                "format", format));

        return converter.convert(Objects.requireNonNull(chatClient.prompt(prompt)
                .call()
                .content()));
    }
}
