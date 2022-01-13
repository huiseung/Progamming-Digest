package com.example.ex1.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;

    public static UserDto of(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
