package com.example.back.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSaveRequestDto {
    private String username;
    private String password;
    private String name;
}
