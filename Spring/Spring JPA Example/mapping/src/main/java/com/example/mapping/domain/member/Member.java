package com.example.mapping.domain.member;

import com.example.mapping.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Builder @AllArgsConstructor
@Entity @NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders =  new ArrayList<>();

    @Column
    private String name;
}
