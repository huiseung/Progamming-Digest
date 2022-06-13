package com.example.back.answer.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AnswerSaveRequestDto {
    private Long questionId;
    private String content;
}
