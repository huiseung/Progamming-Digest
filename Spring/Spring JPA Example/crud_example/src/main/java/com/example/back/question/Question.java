package com.example.back.question;


import com.example.back.answer.Answer;
import com.example.back.question.dto.request.QuestionUpdateRequestDto;
import com.example.back.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(mappedBy = "question", fetch = FetchType.LAZY)
    private Answer answer;
    //
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private boolean isComplete;
    @Column
    @CreatedDate
    private LocalDateTime createAt;
    @Column
    @LastModifiedDate
    LocalDateTime updateAt;
    //
    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.updateAt = LocalDateTime.now();
    }
    //
    public void setUser(User user){
        this.user = user;
        user.addQuestion(this);
    }
    public void setAnswer(Answer answer){
        this.answer = answer;
    }
    public void update(QuestionUpdateRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
    public void checkComplete(){
        this.isComplete = true;
    }
}
