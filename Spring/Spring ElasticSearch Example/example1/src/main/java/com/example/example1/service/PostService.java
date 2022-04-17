package com.example.example1.service;


import com.example.example1.controller.post.request.PostSaveRequestDto;
import com.example.example1.controller.post.request.PostUpdateRequestDto;
import com.example.example1.controller.post.response.PostResponseDto;
import com.example.example1.entity.Post;
import com.example.example1.entity.PostDocument;
import com.example.example1.repository.PostElasticRepository;
import com.example.example1.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostElasticRepository postElasticRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllES(){
        log.info("[Transactional] post findAll");
        List<PostResponseDto> posts = new ArrayList<>();
        for (PostDocument post : postElasticRepository.findAll()) {
            posts.add(PostResponseDto.of(post));
        }
        return posts;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllJPA(){
        log.info("[Transactional] post findAll");
        List<PostResponseDto> posts = new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            posts.add(PostResponseDto.of(post));
        }
        return posts;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findByContentContainES(String content){
        log.info("[Transactional] post findByContentContain, content: "+ content);
        return postElasticRepository.findPostDocumentsByContentContaining(content)
                .stream()
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findByContentContainJPA(String content){
        log.info("[Transactional] post findByContentContain, content: "+ content);
        return postRepository.findPostsByContentContaining(content)
                .stream()
                .map(PostResponseDto::of)
                .collect(Collectors.toList());
    }




    @Transactional(readOnly = true)
    public Post findById(Long id){
        log.info("[Transactional] post findById, id: "+id);
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("find not post"));
    }


    @Transactional
    public PostResponseDto create(PostSaveRequestDto requestDto){
        log.info("[Transactional] post save");
        Post post = Post.builder()
                .tittle(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        log.info("request: "+ requestDto);
        Post savePost = postRepository.save(post);
        postElasticRepository.save(PostDocument.of(savePost));
        return PostResponseDto.of(savePost);
    }

    @Transactional
    public void createAll(List<PostSaveRequestDto> requestDtos){
        List<Post> posts = requestDtos.stream()
                .map(post -> {
                    return Post.builder()
                            .tittle(post.getTitle())
                            .content(post.getContent())
                            .build();
                })
                .collect(Collectors.toList());
        List<Post> savePosts = postRepository.saveAll(posts);
        postElasticRepository.saveAll(savePosts.stream().map(PostDocument::of).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public PostResponseDto readById(Long id){
        return PostResponseDto.of(findById(id));
    }

    @Transactional
    public PostResponseDto update(PostUpdateRequestDto requestDto){
        Post post = findById(requestDto.getId());
        log.info("[Transactional] post update");
        post.update(requestDto);
        Post savePost = postRepository.save(post);
        postElasticRepository.save(PostDocument.of(savePost));
        return PostResponseDto.of(savePost);
    }

    @Transactional
    public void delete(Long id){
        Post post = findById(id);
        postRepository.delete(post);
        postElasticRepository.delete(PostDocument.of(post));
    }

    @Transactional
    public void deleteAll(){
        postRepository.deleteAll();
        postElasticRepository.deleteAll();
    }


}
