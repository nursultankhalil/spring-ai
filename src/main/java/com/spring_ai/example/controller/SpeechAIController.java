package com.spring_ai.example.controller;

import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.SpeechAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/speech")
public class SpeechAIController {

    private final SpeechAIService speechAIService;

    public SpeechAIController(SpeechAIService speechAIService) {
        this.speechAIService = speechAIService;
    }

    @PostMapping
    public byte[] speech(@RequestBody Question question) {
        return speechAIService.speech(question);
    }
}
