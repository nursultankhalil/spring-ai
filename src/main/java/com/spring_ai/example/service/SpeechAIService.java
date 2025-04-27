package com.spring_ai.example.service;

import com.spring_ai.example.model.Question;

public interface SpeechAIService {
    byte[] speech(Question question);
}
