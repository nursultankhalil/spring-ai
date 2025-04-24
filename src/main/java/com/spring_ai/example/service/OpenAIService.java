package com.spring_ai.example.service;

import com.spring_ai.example.model.Answer;
import com.spring_ai.example.model.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
}
