package com.example.back.post;


import com.example.back.post.dto.request.PostUpdateRequestDto;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    //
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Integer viewCount;
    @CreatedDate
    @Column
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column
    private LocalDateTime updateAt;
    //
    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.viewCount = 0;
    }
    @PreUpdate
    public void preUpdate(){
        this.updateAt = LocalDateTime.now();
    }
    //

    public void update(PostUpdateRequestDto requestDto){
        if(requestDto.getTitle() != null){
            this.title = requestDto.getTitle();
        }
        if(requestDto.getContent() != null){
            this.content = requestDto.getContent();
        }
    }
}
