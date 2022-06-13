package com.example.back.review;

import com.example.back.product.Product;
import com.example.back.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name="product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    //
    @Column
    private String content;
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
    public void setUser(User user){
        this.user = user;
    }
    public void setProduct(Product product){
        this.product = product;
    }
}
