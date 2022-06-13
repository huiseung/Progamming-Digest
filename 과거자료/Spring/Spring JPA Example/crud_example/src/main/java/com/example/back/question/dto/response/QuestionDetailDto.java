package com.example.back.question.dto.response;

import com.example.back.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionDetailDto {
    private String title;
    private String questionContent;
    private String userName;
    private String answerContent;
    private boolean isComplete;

    public static QuestionDetailDto of(Question question){
        if(question.isComplete()){
            return QuestionDetailDto.builder()
                    .title(question.getTitle())
                    .questionContent(question.getContent())
                    .userName(question.getUser().getName())
                    .answerContent(question.getAnswer().getContent())
                    .isComplete(question.isComplete())
                    .build();
        }
        else{
            return QuestionDetailDto.builder()
                    .title(question.getTitle())
                    .questionContent(question.getContent())
                    .userName(question.getUser().getName())
                    .isComplete(question.isComplete())
                    .build();
        }
    }
}
