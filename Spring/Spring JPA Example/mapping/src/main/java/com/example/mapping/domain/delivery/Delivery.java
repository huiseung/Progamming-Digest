package com.example.mapping.domain.delivery;


import com.example.mapping.domain.Address;
import com.example.mapping.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    //column
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


    //연관관계에 놓인 상대방에서 연결시킬때 사용하는 method
    public void setOrder(Order order){
        this.order = order;
    }
}
