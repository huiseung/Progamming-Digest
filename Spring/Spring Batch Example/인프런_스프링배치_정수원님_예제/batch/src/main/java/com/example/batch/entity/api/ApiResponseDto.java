package com.example.batch.entity.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponseDto {
    private String status;
    private String message;
}
