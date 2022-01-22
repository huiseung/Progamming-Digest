package com.example.back.question.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionSaveRequestDto {
    private String title;
    private String content;
}
