package com.example.NplusOne.web;

import com.example.NplusOne.domain.member.Member;
import com.example.NplusOne.domain.member.MemberRepository;
import com.example.NplusOne.domain.team.Team;
import com.example.NplusOne.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InitDataGenerator implements ApplicationRunner {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        for(int i = 0; i < 2; i++){
            Team team = createTeam("team" + i);
            createMember("member" + i, team);
        }
    }
    private Team createTeam(String name){
        Team team = teamRepository.findByName(name);
        if(team == null){
            team = Team.builder()
                    .name(name)
                    .build();
            return teamRepository.save(team);
        }
        return team;
    }

    private Member createMember(String name, Team team){
        Member member = memberRepository.findByName(name);
        if(member == null){
            member = Member.builder()
                    .name(name)
                    .team(team)
                    .build();
            return memberRepository.save(member);
        }
        return member;
    }
}
