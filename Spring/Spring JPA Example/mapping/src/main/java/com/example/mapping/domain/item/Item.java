package com.example.mapping.domain.item;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
    @Column
    private int price;
    @Column
    private int stockQuantity;
}
