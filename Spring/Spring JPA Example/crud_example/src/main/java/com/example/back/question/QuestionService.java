package com.example.back.question;

import com.example.back.question.dto.request.QuestionSaveRequestDto;
import com.example.back.question.dto.request.QuestionUpdateRequestDto;
import com.example.back.question.dto.response.QuestionDetailDto;
import com.example.back.question.dto.response.QuestionProfileDto;
import com.example.back.user.User;
import com.example.back.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Question findQuestionById(Long questionId){
        log.info("transactional: findQuestionById");
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("don't find question"));
        log.info("question memory address: "+question);
        return question;
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId){
        log.info("transactional: findUserById");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("don't find user"));
        log.info("user memory address: "+user);
        return user;
    }

    @Transactional
    public Long saveQuestion(Long userId, QuestionSaveRequestDto requestDto){
        Question question = Question.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .isComplete(false)
                .build();
        User user = findUserById(userId);
        question.setUser(user);
        log.info("question memory address: "+question);
        log.info("transactional: saveQuestion");
        return questionRepository.save(question).getId();
    }

    @Transactional(readOnly = true)
    public QuestionDetailDto getQuestion(Long questionId){
        return QuestionDetailDto.of(findQuestionById(questionId));
    }

    @Transactional(readOnly = true)
    public List<QuestionProfileDto> getMyQuestion(Long userId){
        return questionRepository.findMyQuestions(userId)
                .stream().map(QuestionProfileDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateQuestion(QuestionUpdateRequestDto requestDto){
        Question question = findQuestionById(requestDto.getQuestionId());
        log.info("question memory address: "+question);
        question.update(requestDto);
        log.info("transactional: saveQuestion");
        return questionRepository.save(question).getId();
    }

    @Transactional
    public String deleteQuestion(Long questionId){
        Question question = findQuestionById(questionId);
        log.info("transactional: deleteQuestion");
        questionRepository.delete(question);
        return "delete question success";
    }

    @Transactional
    public List<QuestionProfileDto> getQuestionsNotComplete(){
        return questionRepository.findQuestionsNotComplete().stream()
                .map(QuestionProfileDto::of)
                .collect(Collectors.toList());
    }
}
