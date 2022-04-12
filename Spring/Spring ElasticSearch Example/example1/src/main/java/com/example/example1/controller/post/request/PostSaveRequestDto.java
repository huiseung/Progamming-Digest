package com.example.example1.controller.post.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;

    @Override
    public String toString(){
        return "{" +
                "title: " + title +
                " content: "+ content +
                "}";

    }
}
