package com.example.back.user;


import com.example.back.user.dto.request.UserSaveRequestDto;
import com.example.back.user.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/not")
    public List<UserResponseDto> getUsersNotReadOnly(){
        return userService.getUsersNotReadOnly();
    }

    @PostMapping
    public Long saveUser(@RequestBody UserSaveRequestDto requestDto){
        return userService.saveUser(requestDto);
    }

    @DeleteMapping
    public String deleteAll(){
        return userService.destroy();
    }
}
