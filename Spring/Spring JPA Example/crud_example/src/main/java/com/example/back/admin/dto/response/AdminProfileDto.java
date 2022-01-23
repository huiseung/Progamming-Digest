package com.example.back.admin.dto.response;

import com.example.back.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class AdminProfileDto {
    private String name;

    public static AdminProfileDto of(Admin admin){
        return AdminProfileDto.builder()
                .name(admin.getName())
                .build();
    }
}
