package com.example.back.admin.dto.request;


import lombok.*;


@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateRequestDto {
    private Long adminId;
    private String password;
    private String name;
}
