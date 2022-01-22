package com.example.back.admin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AdminSaveRequestDto {
    private String username;
    private String password;
    private String name;
}
