package com.example.NplusOne.domain.team;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder@AllArgsConstructor
@Entity@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

}
