package com.spring_ai.example.controller;

import com.spring_ai.example.model.Answer;
import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/ai")
public class OpenAIController {

    private final OpenAIService openAIService;


    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping()
    public String getAnswer(@RequestBody String question){
        return openAIService.getAnswer(question);
    }

    @PostMapping("/model")
    public Answer getAnswer(@RequestBody Question question){
        return openAIService.getAnswer(question);
    }
}
