package com.example.back.event;


import com.example.back.admin.Admin;
import com.example.back.admin.AdminRepository;
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
    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public Event findEventById(Long eventId){
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("don't find event"));
    }

    @Transactional
    public Long saveEvent(Long adminId, EventSaveRequestDto requestDto){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("don't find admin"));
        Event event = Event.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        event.setAdmin(admin);
        return eventRepository.save(event).getId();
    }

    @Transactional
    public Long updateEvent(EventUpdateRequestDto requestDto){
        Event event = eventRepository.findById(requestDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("don't find event"));
        event.update(requestDto);
        return eventRepository.save(event).getId();
    }

    @Transactional
    public String deleteEvent(Long eventId){
        Event event = findEventById(eventId);
        eventRepository.delete(event);
        return "success delete";
    }
}
