package com.example.cache;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class MemberController {
    private static Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(){
        return "Hello Cache Test Web";
    }

    @GetMapping("/member/nocache/{name}")
    public Member getNoCacheMember(@PathVariable String name){
        long start = System.currentTimeMillis();
        Member member = memberRepository.findByNameNoCache(name);
        long end = System.currentTimeMillis();
        logger.info(name + "의 NoCache 수행시간: " + Long.toString(end-start) + "sec");
        return member;
    }
    @GetMapping("/member/cache/{name}")
    public Member getCacheMember(@PathVariable String name){
        long start = System.currentTimeMillis();
        Member member = memberRepository.findByNameCache(name);
        long end = System.currentTimeMillis();
        logger.info(name + "의 Cache 수행시간: " + Long.toString(end-start) + "sec");
        return member;
    }

    @GetMapping("/member/refresh/{name}")
    public String refresh(@PathVariable String name){
        memberRepository.refresh(name);
        return "cache clear";
    }
}
