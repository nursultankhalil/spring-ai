package com.spring_ai.example.controller;

import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.ImageAIService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("v1/api/image")
public class ImageAIController {

    private final ImageAIService imageAIService;

    public ImageAIController(ImageAIService imageAIService) {
        this.imageAIService = imageAIService;
    }

    @PostMapping(value = "/vision", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String upload(
            @Validated @RequestParam("file")MultipartFile file
            ) throws IOException {
        return imageAIService.getDescription(file);
    }

    @PostMapping
    public byte[] getImage(@RequestBody Question question) {
        return imageAIService.getImage(question);
    }
}
