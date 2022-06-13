package com.example.back;

import com.example.back.post.PostService;
import com.example.back.post.dto.request.PostSaveRequestDto;
import com.example.back.post.dto.request.PostUpdateRequestDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void initDB(@Autowired PostService postService){
        for(int i = 0; i < 10; i++){
            PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                    .title("title"+i)
                    .content("content"+i)
                    .build();
            postService.savePost(requestDto);
        }
    }

    @Nested
    public class getPostTest{
        @Test
        public void success() throws Exception{
            //when
            System.out.println("first get");
            MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/posts/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            //then
            System.out.println("cache get");
            result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/posts/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();

        }
    }

    @Nested
    public class updatePostTest{
        @Test
        @DisplayName("update를 진행하면 케시와 내용이 다르기에, 캐시에 데이터를 지운다")
        public void success() throws Exception{
            //when
            MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/posts/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            PostUpdateRequestDto requestDto = PostUpdateRequestDto
                    .builder()
                    .postId(1L)
                    .title("titleUpdateAfter")
                    .content("contentUpdate")
                    .build();
            String requestBody = objectMapper.writeValueAsString(requestDto);
            result = mockMvc.perform(
                            MockMvcRequestBuilders.patch("/posts")
                                    .content(requestBody)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            //then
            System.out.println("cache get");
            result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/posts/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Nested
    public class getPostsTest{
        @Test
        public void success() throws Exception{
            //when
            System.out.println("first get");
            MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/posts")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            JsonNode resultJson = objectMapper.readTree(result.getResponse().getContentAsString());
            //then
            System.out.println("cache get");
            result = mockMvc.perform(
                            MockMvcRequestBuilders.get("/posts")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            resultJson = objectMapper.readTree(result.getResponse().getContentAsString());
        }
    }
}
