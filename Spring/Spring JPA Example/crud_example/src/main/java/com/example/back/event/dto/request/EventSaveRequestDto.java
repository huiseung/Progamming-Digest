package com.example.back.event.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EventSaveRequestDto {
    private String title;
    private String content;
}
