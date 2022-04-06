package com.example.example1.service;


import com.example.example1.controller.user.request.UserSaveRequestDto;
import com.example.example1.controller.user.response.UserDto;
import com.example.example1.entity.user.User;
import com.example.example1.repository.UserRepository;
import com.example.example1.repository.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserSearchRepository userSearchRepository;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto){
        User user = User.builder()
                .name(userSaveRequestDto.getName())
                .age(userSaveRequestDto.getAge())
                .build();
        User savedUser = userRepository.save(user);
        userSearchRepository.save(savedUser);
        return savedUser.getId();
    }

    @Transactional
    public List<UserDto> findsByAge(Integer age){
        return userSearchRepository.
    }
}
