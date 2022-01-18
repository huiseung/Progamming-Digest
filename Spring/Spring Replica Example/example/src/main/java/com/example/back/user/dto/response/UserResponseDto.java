package com.example.back.user.dto.response;


import com.example.back.post.Post;
import com.example.back.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDto {
    private Long userId;
    private List<String> postTitles;

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .userId(user.getId())
                .postTitles(user.getPosts().stream().map(Post::getTitle).collect(Collectors.toList()))
                .build();
    }
}
