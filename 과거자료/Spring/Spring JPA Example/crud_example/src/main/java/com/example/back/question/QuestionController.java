package com.example.back.question;

import com.example.back.question.dto.request.QuestionSaveRequestDto;
import com.example.back.question.dto.request.QuestionUpdateRequestDto;
import com.example.back.question.dto.response.QuestionDetailDto;
import com.example.back.question.dto.response.QuestionProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/questions")
@Slf4j
@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;
    
    @GetMapping("/{questionId}")
    public QuestionDetailDto getQuestion(
            @PathVariable Long questionId){
        return questionService.getQuestion(questionId);
    }

    @GetMapping("/my-questions")
    public List<QuestionProfileDto> getMyQuestions(
            @RequestParam("userId") Long userId) //security 추가시 수정
    {
        log.info("call get my questions");
        return questionService.getMyQuestion(userId);
    }

    @PostMapping
    public Long saveQuestion(
            @RequestParam("userId") Long userId, // security 적용시 수정
            @RequestBody QuestionSaveRequestDto requestDto){
        log.info("call save question");
        return questionService.saveQuestion(userId, requestDto);
    }

    @PatchMapping
    public Long updateQuestion(
            @RequestParam("userId") Long userId, // security 적용시 수정
            @RequestBody QuestionUpdateRequestDto requestDto
    ){
        log.info("call update question");
        log.info("request: "+requestDto);
        return questionService.updateQuestion(userId, requestDto);
    }
}
