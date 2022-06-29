package com.example.example1.controller.post.response;


import com.example.example1.entity.Post;
import com.example.example1.entity.PostDocument;
import lombok.Getter;


@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;

    public PostResponseDto(
        Long id,
        String tittle,
        String content
    ){
        this.id = id;
        this.title = tittle;
        this.content = content;
    }

    public static PostResponseDto of(Post post){
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent());
    }
    public static PostResponseDto of(PostDocument post){
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent());
    }

}
