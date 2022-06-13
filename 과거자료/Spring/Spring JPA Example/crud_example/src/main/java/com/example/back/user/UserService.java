package com.example.back.user;


import com.example.back.question.dto.response.QuestionProfileDto;
import com.example.back.user.dto.request.UserSaveRequestDto;
import com.example.back.user.dto.response.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(Long userId){
        log.info("findUserById");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("don't find user"));
        log.info("user memory address: "+ user);
        return user;
    }

    @Transactional
    public Long saveUser(UserSaveRequestDto requestDto){
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .build();
        log.info("user memory address: "+user);
        log.info("transactional: saveUser");
        return userRepository.save(user).getId();
    }

    @Transactional
    public UserProfileDto getUser(Long userId){
        return UserProfileDto.of(findUserById(userId));
    }
}
