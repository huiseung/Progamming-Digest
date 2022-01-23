package com.example.back.admin;


import com.example.back.admin.dto.request.AdminSaveRequestDto;
import com.example.back.admin.dto.request.AdminUpdateRequestDto;
import com.example.back.admin.dto.response.AdminProfileDto;
import com.example.back.event.dto.response.EventProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Transactional(readOnly=true)
    public Admin findAdminById(Long adminId){
        return adminRepository.findById(adminId)
                .orElseThrow(()-> new IllegalArgumentException("don't find admin"));
    }

    @Transactional
    public Long saveAdmin(AdminSaveRequestDto requestDto){
        Admin admin = Admin.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .build();
        return adminRepository.save(admin).getId();
    }

    @Transactional(readOnly = true)
    public AdminProfileDto getAdminProfile(Long adminId){
        return AdminProfileDto.of(findAdminById(adminId));
    }

    @Transactional(readOnly = true)
    public List<EventProfileDto> getMyEvents(Long adminId){
        Admin admin = findAdminById(adminId);
        return admin.getEvents().stream()
                .map(EventProfileDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateAdmin(AdminUpdateRequestDto requestDto){
        Admin admin = findAdminById(requestDto.getAdminId());
        admin.update(requestDto);
        return adminRepository.save(admin).getId();
    }

    @Transactional
    public String deleteAdmin(Long adminId){
        Admin admin = findAdminById(adminId);
        adminRepository.delete(admin);
        return "admin delete";
    }
}
