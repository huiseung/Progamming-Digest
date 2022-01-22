package com.example.back.user.dto.response;

import com.example.back.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileDto {
    private String username;
    private String name;

    public static UserProfileDto of(User user){
        return UserProfileDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
}
