package com.example.example1;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello(){
        log.info("[API] hello");
        return "hello";
    }

}
