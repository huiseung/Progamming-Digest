package com.example.example1.controller.post.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String content;
}
