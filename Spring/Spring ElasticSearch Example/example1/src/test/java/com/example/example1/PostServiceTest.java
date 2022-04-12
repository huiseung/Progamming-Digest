package com.example.example1;


import com.example.example1.controller.post.request.PostSaveRequestDto;
import com.example.example1.controller.post.response.PostResponseDto;
import com.example.example1.service.PostService;
import org.elasticsearch.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;

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
        int length = postService.findAllJPA().size();
        long end = System.currentTimeMillis();
        //then
        System.out.println("JPA findAll: "+(end-start)+ "ms");
        assertThat(length).isEqualTo(num);
        ////
        start = System.currentTimeMillis();
        length = postService.findAllES().size();
        end = System.currentTimeMillis();
        //then
        System.out.println("ES findAll: "+(end-start)+ "ms");
        assertThat(length).isEqualTo(num);
        //
        start = System.currentTimeMillis();
        length = postService.findAllJPA().size();
        end = System.currentTimeMillis();
        //then
        System.out.println("JPA findAll: "+(end-start)+ "ms");
        assertThat(length).isEqualTo(num);


        start = System.currentTimeMillis();
        length = postService.findAllES().size();
        end = System.currentTimeMillis();
        System.out.println("ES findAll: "+(end-start)+ "ms");
        assertThat(length).isEqualTo(num);

        //

        postService.deleteAll();
    }
}