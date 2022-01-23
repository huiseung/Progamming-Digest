package com.example.back.question.dto.request;


import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateRequestDto {
    private Long questionId;
    private String title;
    private String content;
}
