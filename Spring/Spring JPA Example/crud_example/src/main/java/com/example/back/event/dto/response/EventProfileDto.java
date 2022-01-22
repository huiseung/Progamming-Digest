package com.example.back.event.dto.response;


import com.example.back.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EventProfileDto {
    private String title;
    private String content;

    public static EventProfileDto of(Event event){
        return EventProfileDto.builder()
                .title(event.getTitle())
                .content(event.getContent())
                .build();
    }
}
