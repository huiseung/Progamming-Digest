package com.example.NplusOne;

import com.example.NplusOne.domain.member.Member;
import com.example.NplusOne.domain.member.MemberRepository;
import com.example.NplusOne.domain.team.Team;
import com.example.NplusOne.domain.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void OneWayManyToOneMappingNplusOneProblem() {
        for (int i = 0; i < 2; i++) {
            Team team = Team.builder()
                    .name("team" + i)
                    .build();

            Team savedTeam = teamRepository.save(team);
            Member member = Member.builder()
                    .name("member" + i)
                    .team(savedTeam)
                    .build();
            memberRepository.save(member);
        }
        //then
        memberRepository.findAll().forEach(System.out::println);
        //@SpringBootTest일땐 select가 3개, 왜냐 @SpringBootTest에는 @Transection이 안 붙어 있다
        //@DataJpaTest일땐 select가 1개, 왜냐 @DataJpaTest 에는 @Transection이 있다
        //Member가 set하려는 Team이 이미 같은 transection상에서 persistence context에 등록되어 mange되고 있기에 select Team은 발생하지 않는다
    }
}

