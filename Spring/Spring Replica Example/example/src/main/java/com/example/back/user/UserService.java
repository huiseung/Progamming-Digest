package com.example.back.user;


import com.example.back.user.dto.request.UserSaveRequestDto;
import com.example.back.user.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long saveUser(UserSaveRequestDto requestDto){
        Long userId = 0L;
        Long start = System.currentTimeMillis();
        for(int i=0; i < 100; i++){
            User user = User.builder()
                    .username(requestDto.getUsername()+"_"+i)
                    .nickname(requestDto.getNickname()+"_"+i)
                    .build();
            User saveUser = userRepository.save(user);
            if(i % 10 == 0){
                userRepository.delete(saveUser);
            }
        }
        Long end = System.currentTimeMillis();
        log.info("save 1000 user {}", end-start);
        return userId;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers(){
        Long start = System.currentTimeMillis();
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<UserResponseDto> users = userRepository.findAll(pageRequest).stream()
                .map(UserResponseDto::of).collect(Collectors.toList());
        Long end = System.currentTimeMillis();
        log.info("find 5 users {} at readOnly=ture", end-start);
        return users;
    }

    @Transactional(readOnly = false)
    public List<UserResponseDto> getUsersNotReadOnly(){
        Long start = System.currentTimeMillis();
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<UserResponseDto> users = userRepository.findAll(pageRequest).stream()
                .map(UserResponseDto::of).collect(Collectors.toList());
        Long end = System.currentTimeMillis();
        log.info("find 5 users {} at readOnly=false", end-start);
        return users;
    }

    @Transactional
    public String destroy(){
        userRepository.deleteAll();
        return "success";
    }
}
