package com.example.example1.entity;


import com.example.example1.controller.post.request.PostUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Table(name="posts")
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @Column
    private String title;
    @Lob
    @Column
    private String content;
    //
    @Builder
    public Post(
            Long id,
            String tittle,
            String content
    ){
        this.id = id;
        this.title = tittle;
        this.content = content;
    }
    //
    public void update(PostUpdateRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
    //
    @Override
    public String toString(){
        return new StringBuffer()
                .append("post {")
                .append("id= ").append(id)
                .append("title= ").append(title)
                .append("content= ").append(content)
                .append("}")
                .toString();
    }

}
