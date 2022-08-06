package com.blog.blogservice.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
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
