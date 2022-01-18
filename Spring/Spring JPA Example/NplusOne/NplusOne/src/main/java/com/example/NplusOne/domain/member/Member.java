package com.example.NplusOne.domain.member;


import com.example.NplusOne.domain.team.Team;
import lombok.*;

import javax.persistence.*;


@Getter
@ToString
@Builder@AllArgsConstructor
@Entity@NoArgsConstructor
@NamedEntityGraph(name = "MemberWithTeam", attributeNodes = @NamedAttributeNode("team"))
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Team team;

    //@ManyToOne(fetch = FetchType.LAZY) 하면 Team 객체를 proxy 로 가져와 team 정보를 필요로 하는 순간까지는 member 에 대한 select 만 발생하게 지연시킬 수 있다
    //proxy 객체를 유지하면서 정보는 얻고 싶다면 @Getter@Setter가 달린 Dto를 만들어라

    @Column
    private String name;
}
