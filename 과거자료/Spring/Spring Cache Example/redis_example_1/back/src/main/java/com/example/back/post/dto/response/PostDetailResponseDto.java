package com.example.back.post.dto.response;

import com.example.back.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private String createAt;
    private Integer viewCount;

    public static PostDetailResponseDto of(Post post){
        return PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .viewCount(post.getViewCount())
                .build();
    }
}
