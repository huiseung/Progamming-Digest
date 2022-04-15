package com.example.example1.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Getter
@Document(indexName="posts")
public class PostDocument implements Serializable {
    @Id
    private Long id;
    @Field
    private String title;
    @Field
    private String content;

    @Builder
    public PostDocument(
        Long id,
        String title,
        String content
    ){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static PostDocument of(Post post){
        return PostDocument.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
