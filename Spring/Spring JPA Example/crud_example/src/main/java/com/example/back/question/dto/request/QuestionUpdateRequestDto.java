package com.example.back.question.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class QuestionUpdateRequestDto {
    private Long questionId;
    private String title;
    private String content;
}
