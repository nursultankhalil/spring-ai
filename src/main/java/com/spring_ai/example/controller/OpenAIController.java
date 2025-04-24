package com.spring_ai.example.controller;

import com.spring_ai.example.model.Answer;
import com.spring_ai.example.model.CapitalRequest;
import com.spring_ai.example.model.CapitalResponse;
import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.OpenAIService;
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

    @PostMapping("/capital")
    public Answer getAnswer(@RequestBody CapitalRequest capital) {
        return openAIService.getAnswer(capital);
    }

    @PostMapping("/capital-more-info")
    public Answer getAnswerMoreInfo(@RequestBody CapitalRequest capital) {
        return openAIService.getAnswerMoreInfo(capital);
    }

    @PostMapping("/capital-json")
    public CapitalResponse getAnswerJson(@RequestBody CapitalRequest capital) {
        return openAIService.getAnswerJson(capital);
    }
}
