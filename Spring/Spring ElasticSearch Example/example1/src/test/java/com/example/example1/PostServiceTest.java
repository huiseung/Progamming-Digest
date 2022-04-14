package com.example.example1;


import com.example.example1.controller.post.request.PostSaveRequestDto;
import com.example.example1.controller.post.response.PostResponseDto;
import com.example.example1.service.PostService;
import org.elasticsearch.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @BeforeEach
    public void before(@Autowired PostService postService){
        postService.deleteAll();
    }
    @AfterEach
    public void after(@Autowired PostService postService){
        postService.deleteAll();
    }

    @Test
    public void saveTest(){
        //given
        final String title = "title1";
        final String content = "content1";
        PostSaveRequestDto requestDto = new PostSaveRequestDto(title, content);
        //when
        PostResponseDto responseDto = postService.create(requestDto);
        //then
        assertThat(responseDto.getTitle()).isEqualTo(title);
    }

    @Test
    public void findAll(){
        //given
        int num = 100;
        for(int i = 0; i < num; i++){
            PostSaveRequestDto requestDto = new PostSaveRequestDto("title"+i, "content"+i);
            postService.create(requestDto);
        }
        //when
        long start = System.currentTimeMillis();
        List<PostResponseDto> posts = postService.findAllJPA();
        long length = posts.size();
        long end = System.currentTimeMillis();
        //then
        System.out.println("JPA findAll: "+(end-start)+ "ms");
        assertThat(length).isEqualTo(num);

    }

    @Test
    public void findByContentContaining(){
        //given
        int num = 10;
        for(int i = 0; i < num; i++){
            PostSaveRequestDto requestDto = new PostSaveRequestDto("title"+i, "content"+i);
            postService.create(requestDto);
        }
        for(int i = num; i < 2*num; i++){
            PostSaveRequestDto requestDto = new PostSaveRequestDto("title"+i, "is this content"+i);
            postService.create(requestDto);
        }
        //when
        long start = System.currentTimeMillis();
        List<PostResponseDto> postsES = postService.findByContentContainES("content");
        long end = System.currentTimeMillis();
        System.out.println("ES: "+(end-start));
        start = System.currentTimeMillis();
        List<PostResponseDto> postsJS = postService.findByContentContainES("content");
        end = System.currentTimeMillis();
        System.out.println("JPA: "+(end-start));

        //then
       assertThat(postsES.size()).isEqualTo(num*2);
       assertThat(postsJS.size()).isEqualTo(num*2);
    }
}
