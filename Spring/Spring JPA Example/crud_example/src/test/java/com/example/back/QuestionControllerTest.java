package com.example.back;

import com.example.back.question.QuestionService;
import com.example.back.question.dto.request.QuestionSaveRequestDto;
import com.example.back.question.dto.request.QuestionUpdateRequestDto;
import com.example.back.user.UserService;
import com.example.back.user.dto.request.UserSaveRequestDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@AutoConfigureMockMvc
@SpringBootTest
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    public static Long userId;
    public static Long questionId;

    @BeforeAll
    public static void initDb(
            @Autowired UserService userService,
            @Autowired QuestionService questionService) {
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .username("username")
                .password("1234")
                .name("name")
                .build();
        userId = userService.saveUser(requestDto);
        for(int i = 0; i < 3; i++){
            QuestionSaveRequestDto questionSaveRequestDto = QuestionSaveRequestDto.builder()
                    .title("pre title"+i)
                    .content("pre content"+i)
                    .build();
            questionId = questionService.saveQuestion(userId, questionSaveRequestDto);
        }
    }

    @Order(1)
    @Nested
    @DisplayName("question save test")
    class saveQuestion{
        @Test
        @DisplayName("success")
        public void success() throws Exception{
            QuestionSaveRequestDto requestDto = QuestionSaveRequestDto.builder()
                    .title("title2")
                    .content("content2")
                    .build();
            String requestBody = objectMapper.writeValueAsString(requestDto);
            MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/questions")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        }
    }

    @Order(2)
    @Nested
    @DisplayName("get question test")
    class getQuestion{
        @Test
        @DisplayName("success")
        public void success() throws Exception{
            MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/questions/"+questionId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            JsonNode resultJson = objectMapper.readTree(result.getResponse().getContentAsString());
        }
    }

    @Order(3)
    @Nested
    @DisplayName("get my questions test")
    class getMyQuestions{
        @Test
        @DisplayName("success: N+1 problem ????????????()")
        public void success() throws Exception{
            MvcResult result = mockMvc.perform(
                    MockMvcRequestBuilders.get("/questions/my-questions?userId="+userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
            JsonNode resultJson = objectMapper.readTree(result.getResponse().getContentAsString());
        }
    }

    @Order(4)
    @Nested
    @DisplayName("update question test")
    class updateQuestion{
        @Test
        @DisplayName("success")
        public void success() throws Exception{
            QuestionUpdateRequestDto requestDto = QuestionUpdateRequestDto.builder()
                    .questionId(questionId)
                    .title("update title")
                    .content("update content")
                    .build();
            String requestBody = objectMapper.writeValueAsString(requestDto);
            System.out.println(requestBody);
            MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.patch("/questions?userId="+userId)
                                    .content(requestBody)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }
}
