package com.example.example1.controller.user;

import com.example.example1.controller.user.request.UserSaveRequestDto;
import com.example.example1.controller.user.response.UserDto;
import com.example.example1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    public Long save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

    @GetMapping("/{age}")
    public List<UserDto> findsByAge(@PathVariable Integer age){
        return userService.findsByAge(age);
    }
}
