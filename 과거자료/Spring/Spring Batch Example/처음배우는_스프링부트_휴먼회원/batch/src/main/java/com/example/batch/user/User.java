package com.example.batch.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    @Column
    private UserStatus status;

    @Column
    private LocalDateTime createDateTime;
    @Column
    private LocalDateTime updateDateTime;


    public User setInactive(){
        this.status = UserStatus.INACTIVE;
        return this;
    }
}
