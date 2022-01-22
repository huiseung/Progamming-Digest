package com.example.back.event;


import com.example.back.admin.Admin;
import com.example.back.event.dto.request.EventUpdateRequestDto;
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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @JoinColumn(name="admin_id", nullable = false)
    @ManyToOne
    private Admin admin;
    //
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @CreatedDate
    private LocalDateTime createAt;
    @Column
    @LastModifiedDate
    LocalDateTime updateAt;
    //
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
    public void setAdmin(Admin admin){
        this.admin = admin;
    }
    public void update(EventUpdateRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
