package com.example.back;


import com.example.back.admin.AdminService;
import com.example.back.admin.dto.request.AdminSaveRequestDto;
import com.example.back.admin.dto.request.AdminUpdateRequestDto;
import com.example.back.admin.dto.response.AdminProfileDto;
import com.example.back.event.EventService;
import com.example.back.event.dto.request.EventSaveRequestDto;
import com.example.back.event.dto.response.EventProfileDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    public static Long adminId;

    @BeforeAll
    public static void initDB(@Autowired AdminService adminService,
                              @Autowired EventService eventService){
        AdminSaveRequestDto requestDto = AdminSaveRequestDto
                .builder()
                .username("admin")
                .password("1234")
                .name("name")
                .build();
        adminId = adminService.saveAdmin(requestDto);
        for(int i = 0; i < 3; i++){
            EventSaveRequestDto eventSaveRequestDto = EventSaveRequestDto
                    .builder()
                    .title("event 1")
                    .content("content 1")
                    .build();
            eventService.saveEvent(adminId, eventSaveRequestDto);
        }
    }

    @Order(1)
    @Nested
    class AdminGetTest{
        @Test
        public void success() throws Exception{
            AdminProfileDto response = adminService.getAdminProfile(adminId);
            System.out.println(response);
        }
    }

    @Order(2)
    @Nested
    class GetEventTest{
        @Test
        public void success() throws Exception{
            List<EventProfileDto> events = adminService.getMyEvents(adminId);
        }
    }

    @Order(3)
    @Nested
    class AdminUpdateTest{
        @Test
        public void success() throws Exception{
            AdminUpdateRequestDto requestDto = AdminUpdateRequestDto
                    .builder()
                    .adminId(adminId)
                    .name("update admin")
                    .build();
            adminService.updateAdmin(requestDto);
        }
    }

    @Order(4)
    @Nested
    class AdminDeleteTest{
        @Test
        public void success() throws Exception{
            adminService.deleteAdmin(adminId);
            AdminSaveRequestDto requestDto = AdminSaveRequestDto
                    .builder()
                    .username("admin")
                    .password("1234")
                    .name("name")
                    .build();
            adminId = adminService.saveAdmin(requestDto);
        }
    }
}
