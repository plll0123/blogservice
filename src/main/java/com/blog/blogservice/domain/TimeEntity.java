package com.blog.blogservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;
//
//    @PrePersist
//    public void onPrePersist(){
//        this.createDate = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        this.modifiedDate = this.createDate;
//    }
//
//    @PreUpdate
//    public void onPreUpdate(){
//        this.modifiedDate = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//    }
//}
}
