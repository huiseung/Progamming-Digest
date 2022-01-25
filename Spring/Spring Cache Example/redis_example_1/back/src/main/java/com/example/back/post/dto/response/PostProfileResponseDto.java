package com.example.back.post.dto.response;

import com.example.back.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostProfileResponseDto {
    private Long id;
    private String title;
    private Integer viewCount;

    public static PostProfileResponseDto of(Post post){
        return PostProfileResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .viewCount(post.getViewCount())
                .build();
    }
}
