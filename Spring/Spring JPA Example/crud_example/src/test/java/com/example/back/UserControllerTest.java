package com.example.back;



import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private static Long userId;
//    private static Long postId;
//
//    @BeforeAll
//    public static void initDb(@Autowired PostService postService,
//                              @Autowired UserService userService){
//        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
//                .username("user1")
//                .password("1234")
//                .build();
//        userId = userService.saveUser(userSaveRequestDto);
//        for(int i = 0; i < 2; i++){
//            PostSaveRequestDto postRequestDto = PostSaveRequestDto.builder()
//                    .title("title"+i)
//                    .content("content"+i)
//                    .build();
//            postId = postService.savePost(userId, postRequestDto);
//        }
//         userSaveRequestDto = UserSaveRequestDto.builder()
//                .username("user2")
//                .password("1234")
//                .build();
//        userId = userService.saveUser(userSaveRequestDto);
//        for(int i = 0; i < 2; i++){
//            PostSaveRequestDto postRequestDto = PostSaveRequestDto.builder()
//                    .title("title"+i)
//                    .content("content"+i)
//                    .build();
//            postId = postService.savePost(userId, postRequestDto);
//        }
//    }
//    @Order(1)
//    @Nested
//    @DisplayName("saveUser api test")
//    class saveUserTest{
//        @Test
//        @DisplayName("saveUser success")
//        public void success() throws Exception{
//
//        }
//    }
//    @Order(2)
//    @Nested
//    @DisplayName("getMyPosts api test")
//    class getMyPostsTest{
//        @Test
//        @DisplayName("getMyPosts success")
//        public void success() throws Exception{
//            MvcResult result = mockMvc.perform(
//                    MockMvcRequestBuilders.get("/users/"+userId)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .contentType(MediaType.APPLICATION_JSON)
//            )
//                    .andExpect(status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//        }
//    }
//
//    @Order(3)
//    @Nested
//    @DisplayName("getAllUsers api test, N+1")
//    class getAllUsersTest{
//        @Test
//        @DisplayName("getAllUsers success: user 마다 OneToMany 객체에 select 가 발생하지 않아야 효울적이다")
//        public void success() throws Exception{
//            MvcResult result = mockMvc.perform(
//                            MockMvcRequestBuilders.get("/users")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .contentType(MediaType.APPLICATION_JSON)
//                    )
//                    .andExpect(status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//
//            JsonNode users = objectMapper.readTree(result.getResponse().getContentAsString());
//            System.out.println("length: "+users.size());
//        }
//    }
}
