package com.example.ex1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/home")
@RestController
public class HomeController {
    @GetMapping
    public String hello(){
        log.info("hello api call");
        return "hello";
    }
}
