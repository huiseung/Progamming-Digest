package com.example.back.post;

import com.example.back.post.dto.request.PostSaveRequestDto;
import com.example.back.post.dto.request.PostUpdateRequestDto;
import com.example.back.post.dto.response.PostDetailResponseDto;
import com.example.back.post.dto.response.PostProfileResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long savePost(PostSaveRequestDto requestDto){
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId){
        log.info("transaction: findPostById");
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("don't find post"));
    }


    @Transactional(readOnly = true)
    public PostDetailResponseDto getPost(Long postId){
        return PostDetailResponseDto.of(findPostById(postId));
    }


    @Transactional(readOnly = true)
    public List<PostProfileResponseDto> getPosts(int page){
        log.info("transaction: findPostAll");
        Pageable pageable = PageRequest.of(page, 5, Sort.by("createAt").descending());
        return postRepository.findAll(pageable).stream()
                .map(PostProfileResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updatePost(PostUpdateRequestDto requestDto){
        Post post = findPostById(requestDto.getPostId());
        post.update(requestDto);
        return postRepository.save(post).getId();
    }

    @Transactional
    public String deletePost(Long postId){
        Post post = findPostById(postId);
        postRepository.delete(post);
        return "post delete success";
    }
}
