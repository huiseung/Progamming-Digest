package com.example.example1.controller.user.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private Integer age;
}
