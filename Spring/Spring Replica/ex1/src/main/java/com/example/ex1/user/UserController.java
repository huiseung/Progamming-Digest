package com.example.ex1.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/{userId}")
    public UserDto find(@PathVariable("userId")Long id){
        log.info("find api call");
        return userService.find(id);
    }

    @PostMapping
    public UserDto save(@RequestBody UserSaveRequestDto requestDto){
        log.info("save api call");
        return userService.save(requestDto);
    }

    @PutMapping
    public UserDto update(@RequestBody UserUpdateRequestDto requestDto){
        log.info("update api call");
        return userService.update(requestDto);
    }
}
