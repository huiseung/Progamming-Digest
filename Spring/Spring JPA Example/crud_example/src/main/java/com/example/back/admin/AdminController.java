package com.example.back.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminController {
    private final AdminService adminService;


}
