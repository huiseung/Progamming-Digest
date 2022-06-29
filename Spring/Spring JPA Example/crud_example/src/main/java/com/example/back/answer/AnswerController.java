package com.example.back.answer;


import com.example.back.answer.dto.request.AnswerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/answers")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public Long saveAnswer(
            @RequestParam("adminId") Long adminId, //security 적용시 수정
            @RequestBody AnswerSaveRequestDto requestDto
            ){
        return answerService.saveAnswer(adminId, requestDto);
    }
}
