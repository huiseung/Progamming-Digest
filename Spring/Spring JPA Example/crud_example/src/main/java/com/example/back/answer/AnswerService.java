package com.example.back.answer;


import com.example.back.admin.Admin;
import com.example.back.admin.AdminRepository;
import com.example.back.answer.dto.request.AnswerSaveRequestDto;
import com.example.back.question.Question;
import com.example.back.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public Long saveAnswer(Long adminId, AnswerSaveRequestDto requestDto){
        Answer answer = Answer.builder()
                .content(requestDto.getContent())
                .build();
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("don't find admin"));
        Question question = questionRepository.findById(requestDto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("don't find question"));
        question.checkComplete();
        question.setAnswer(answer);
        questionRepository.save(question);
        answer.setAdmin(admin);
        return answerRepository.save(answer).getId();
    }
}
