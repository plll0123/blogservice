package com.blog.blogservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    private String name;
    private String description;
    private String displayType;

    @Builder
    public Category(Blog blog, String name, String description, String displayType) {
        this.blog = blog;
        this.name = name;
        this.description = description;
        this.displayType = displayType;
    }
}
