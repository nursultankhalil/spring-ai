package com.spring_ai.example.service;

import com.spring_ai.example.model.Question;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageAIService {
    byte[] getImage(Question question);

    String getDescription(MultipartFile file) throws IOException;
}
