package com.example.NplusOne.web.controller;

import com.example.NplusOne.domain.member.Member;
import com.example.NplusOne.domain.member.MemberRepository;
import com.example.NplusOne.web.dto.MemberListResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    //기본 findAll은 저장된 team 수만큼 select문 발생
    //MemberEntity쪽에 @NamedEntityGraph(name = "MemberWithTeam", attributeNodes = @NamedAttributeNode("team")) 를 추가하고
    //MemberRepository의 findAll을 @EntityGraph("MemberWithTeam") 붙여 재정의하면 select문 하나로 team 정보까지 가져올 수 있다
    //MemberRepository에 @Query("SELECT m FROM Memeber m JOIN FETCH m.team")를 달아 직접 fetch join 을 구현하는 방식도 있다.
    //TeamRepository에 @Query("SELECT t FROM Team t JOIN FETCH t.members")를 달아 직접 fetch join 을 구현하는 방식도 있다.
    @GetMapping("/members")
    @ResponseBody
    public List<Member> members(){
        return memberRepository.findAll();
    }

    //ManyToOne(fetch = FetchType.LAZY)를 설정하고 api를 다음과 같이 바꾸면 select문 1번만 발생
//    @GetMapping("/members")
//    @ResponseBody
//    public List<MemberListResponseDto> members(){
//        ModelMapper modelMapper = new ModelMapper();
//        //gradle 의존성 추가 implementation('org.modelmapper:modelmapper:2.4.4')
//        return memberRepository.findAll().stream()
//                .map(m -> modelMapper.map(m, MemberListResponseDto.class))
//                .collect(Collectors.toList());
//    }

}
