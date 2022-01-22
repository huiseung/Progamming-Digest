package com.example.back.event.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EventUpdateRequestDto {
    private Long eventId;
    private String title;
    private String content;
}
