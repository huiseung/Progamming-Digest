package com.example.back.answer;

import com.example.back.admin.Admin;
import com.example.back.question.Question;
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
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @JoinColumn(name="admin_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;
    @JoinColumn(name="question_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Question question;
    @Column
    private String content;
    //
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
    public void setAdmin(Admin admin){
        this.admin = admin;
        admin.addAnswer(this);
    }
    public void setQuestion(Question question){
        this.question = question;
        question.setAnswer(this);
    }
}
