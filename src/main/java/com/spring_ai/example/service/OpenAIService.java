package com.spring_ai.example.service;

import com.spring_ai.example.model.Answer;
import com.spring_ai.example.model.CapitalRequest;
import com.spring_ai.example.model.CapitalResponse;
import com.spring_ai.example.model.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
    Answer getAnswer(CapitalRequest capital);
    Answer getAnswerMoreInfo(CapitalRequest capital);
    CapitalResponse getAnswerJson(CapitalRequest capital);
}
