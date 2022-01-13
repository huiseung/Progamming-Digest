package com.example.ex1.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto save(UserSaveRequestDto requestDto){
        log.debug("save="+requestDto.toString());
        User user = User.builder()
                .name(requestDto.getName())
                .build();
        return UserDto.of(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto find(Long id){
        log.debug("find="+id.toString());
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(""));
        return UserDto.of(user);
    }

    public UserDto update(UserUpdateRequestDto requestDto){
        log.debug("update="+requestDto.toString());
        User user = userRepository.findById(requestDto.getId())
                .orElseThrow(()->new IllegalArgumentException(""));
        user.update(requestDto.getName());
        return UserDto.of(userRepository.save(user));
    }


}
