package com.example.back.admin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class AdminUpdateRequestDto {
    private Long adminId;
    private String password;
    private String name;
}
