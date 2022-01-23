package com.example.back.user;


import com.example.back.user.dto.request.UserSaveRequestDto;
import com.example.back.user.dto.response.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {;

    private final UserService userService;

    @PostMapping
    public Long saveUser(
            @RequestBody UserSaveRequestDto requestDto){
        return userService.saveUser(requestDto);
    }

    @GetMapping
    public UserProfileDto getUser(
            @RequestParam("userId") Long userId //security 추가시 수정
    ){
        return userService.getUser(userId);
    }

}
