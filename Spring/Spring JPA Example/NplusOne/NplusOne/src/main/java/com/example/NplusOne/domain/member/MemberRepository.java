package com.example.NplusOne.domain.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    @EntityGraph("MemberWithTeam") //fetch outer join 을 실행함
    List<Member> findAll();
}
