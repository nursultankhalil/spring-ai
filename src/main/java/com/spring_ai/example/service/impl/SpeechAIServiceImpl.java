package com.spring_ai.example.service.impl;

import com.spring_ai.example.model.Question;
import com.spring_ai.example.service.SpeechAIService;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SpeechAIServiceImpl implements SpeechAIService {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public SpeechAIServiceImpl(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    @Override
    public byte[] speech(Question question) {

        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .model("tts-1")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0f)
                .build();

        SpeechResponse result = openAiAudioSpeechModel.call(new SpeechPrompt(question.question(), options));
        return result.getResult().getOutput();
    }
}
