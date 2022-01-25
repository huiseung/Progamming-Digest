package com.example.back.post;


import com.example.back.post.dto.request.PostSaveRequestDto;
import com.example.back.post.dto.request.PostUpdateRequestDto;
import com.example.back.post.dto.response.PostDetailResponseDto;
import com.example.back.post.dto.response.PostProfileResponseDto;
import com.example.back.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping
    public Long savePost(@RequestBody PostSaveRequestDto requestDto){
        return postService.savePost(requestDto);
    }

    @Cacheable(value="post", key="#postId")
    @GetMapping("/{postId}")
    public ResponseDto getPost(@PathVariable Long postId){
        log.info("call getPost");
        PostDetailResponseDto post = postService.getPost(postId);
        return ResponseDto.builder()
                        .success(true)
                        .status(200)
                        .response(post)
                        .build();
    }

    @Cacheable(value="posts", key="#page")
    @GetMapping
    public ResponseDto getPosts(@RequestParam(value = "page", defaultValue = "0") int page) throws InterruptedException {
        log.info("call getPosts");
        List<PostProfileResponseDto> posts = postService.getPosts(page);
        return ResponseDto.builder()
                        .success(true)
                        .status(200)
                        .response(posts)
                        .build();
    }

    @CacheEvict(value="post", key="#requestDto.getPostId()")
    @PatchMapping
    public Long updatePost(@RequestBody PostUpdateRequestDto requestDto){
        return postService.updatePost(requestDto);
    }

    @CacheEvict(value="post", key="#postId")
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }
}
