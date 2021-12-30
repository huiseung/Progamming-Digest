package com.example.mapping.domain.order;


import com.example.mapping.domain.OrderItem.OrderItem;
import com.example.mapping.domain.delivery.Delivery;
import com.example.mapping.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    //연관 관계속 tuple 추가 method 들
    //양방향은 사실 단방향 두개 이기에 양쪽 다 set 을 해야한다
    public void setMember(Member member){
        if(this.member != null){
            this.member.getOrders().remove(this);
        }
        member.getOrders().add(this);
        this.member = member;
    }

    public void setDelivery(Delivery delivery){
        if(this.delivery != null){
            this.delivery = null;
        }
        delivery.setOrder(this);
        this.delivery = delivery;
    }

    public void addOrderItem(OrderItem orderItem){
        if(orderItem.getOrder() != this){
            orderItem.setOrder(this);
        }
        orderItems.add(orderItem);
    }

    //static factory method
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        return order;
    }
}
