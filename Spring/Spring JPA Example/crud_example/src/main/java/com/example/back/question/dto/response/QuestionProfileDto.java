package com.example.back.question.dto.response;


import com.example.back.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionProfileDto {
    private String title;
    private String userName;
    private boolean isComplete;

    public static QuestionProfileDto of(Question question){
        return QuestionProfileDto.builder()
                .title(question.getTitle())
                .userName(question.getUser().getName())
                .isComplete(question.isComplete())
                .build();
    }
}
