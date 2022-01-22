package com.example.back.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.isComplete = false")
    List<Question> findQuestionsNotComplete();

    @Query("select q from Question q join fetch q.user join fetch q.answer where q.user.id = ?1")
    List<Question> findMyQuestions(Long userId);
}
