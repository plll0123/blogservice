package com.blog.blogservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post extends TimeEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    private String title;
    private String content;

    @Builder
    public Post(Blog blog, String title, String content) {
        this.blog = blog;
        this.title = title;
        this.content = content;
    }
}
