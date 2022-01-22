package com.example.back.event;


import com.example.back.event.dto.request.EventSaveRequestDto;
import com.example.back.event.dto.request.EventUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    @Transactional
    public Long saveEvent(EventSaveRequestDto requestDto){
        Event event = Event.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        return eventRepository.save(event).getId();
    }

    @Transactional
    public Long updateEvent(EventUpdateRequestDto requestDto){
        Event event = eventRepository.findById(requestDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("don't find event"));
        event.update(requestDto);
        return eventRepository.save(event).getId();
    }
}
