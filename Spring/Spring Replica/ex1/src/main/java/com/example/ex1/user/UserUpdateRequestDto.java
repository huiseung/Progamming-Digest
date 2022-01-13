package com.example.ex1.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserUpdateRequestDto {
    private Long id;
    private String name;
}
