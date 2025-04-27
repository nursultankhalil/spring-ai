package com.spring_ai.example.service.impl;

import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.ImageAIService;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImageAIServiceImpl implements ImageAIService {

    private final ImageModel imageModel;
    private final ChatModel chatModel;

    public ImageAIServiceImpl(ImageModel imageModel, ChatModel chatModel) {
        this.imageModel = imageModel;
        this.chatModel = chatModel;
    }

    @Override
    public byte[] getImage(Question question) {

        var option = OpenAiImageOptions.builder()
                .withWidth(1024)
                .withHeight(1024)
                .withResponseFormat("b64_json")
                .withModel("dall-e-3")
                .withQuality("hd")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), option);

        ImageResponse call = imageModel.call(imagePrompt);

        return Base64.getDecoder().decode(call.getResult().getOutput().getB64Json());
    }

    @Override
    public String getDescription(MultipartFile file) throws IOException {

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.CHATGPT_4_O_LATEST.getValue())
                .build();

        var userMessage = new UserMessage("Explain What do you see in this picture?",
                List.of(new Media(MimeTypeUtils.IMAGE_JPEG, file.getResource())));

        ChatResponse response = chatModel.call(new Prompt(List.of(userMessage), options));

        return response.getResult().getOutput().toString();
    }
}
