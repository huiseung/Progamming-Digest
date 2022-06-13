package com.example.back.order;


import com.example.back.orderDetail.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name="orders")
@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @Builder.Default
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
    //
    @Column
    @CreatedDate
    private LocalDateTime createAt;
    @Column
    @LastModifiedDate
    LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.updateAt = LocalDateTime.now();
    }
    //
    public void addOrderDetail(OrderDetail orderDetail){
        this.orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }
}
