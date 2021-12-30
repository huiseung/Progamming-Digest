package com.example.mapping.domain;

import com.example.mapping.domain.delivery.Delivery;
import com.example.mapping.domain.delivery.DeliveryRepository;
import com.example.mapping.domain.item.ItemRepository;
import com.example.mapping.domain.member.Member;
import com.example.mapping.domain.member.MemberRepository;
import com.example.mapping.domain.order.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void member_create(){
        //given
        Member member = Member.builder()
                .name("member1")
                .build();
        //when
        Long savedId = memberRepository.save(member).getId();
        Member findMember = memberRepository.findById(savedId)
                .orElseThrow(()->new IllegalArgumentException("그런 member 없습니다"));
        //then
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void order_create(){
        //given
        Member member = Member.builder()
                .name("member1")
                .build();
        Delivery delivery = Delivery.builder()
                .build();

    }
}
