package com.example.mapping.domain.OrderItem;


import com.example.mapping.domain.item.Item;
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
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Order order;

    @ManyToOne
    @JoinColumn
    private Item item;

    @Column
    private int orderPrice;
    @Column
    private int count;

    //
    public void setOrder(Order order){
        this.order = order;
    }

}
