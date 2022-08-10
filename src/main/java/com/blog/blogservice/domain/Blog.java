package com.blog.blogservice.domain;

import com.blog.blogservice.exception.NonUniqueBlogException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.blog.blogservice.domain.Status.OPERATION;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Blog {

    @Id @GeneratedValue
    @Column(name = "blog_id")
    private Long id;

    private String title;
    private String tag;

    @Enumerated(STRING)
    private Status status;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "blog", cascade = ALL, orphanRemoval = true)
    private final List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "blog", cascade = ALL, orphanRemoval = true)
    private final List<Category> categories = new ArrayList<>();

    @Builder
    private Blog(Member member, String title, String tag){
        System.out.println("Blog.Blog");
        relationWithMember(member);
        this.categories.add(defaultCategory(this));
        this.title = title;
        this.tag = tag;
        this.status = OPERATION;
    }

    public static Blog createBlog(Member member, String title, String tag){
        return Blog.builder()
                .member(member)
                .title(title)
                .tag(tag)
                .build();
    }

    private Category defaultCategory(Blog blog) {
        return Category.builder()
                .blog(blog)
                .description("기본 제공 카테고리")
                .displayType("제목 + 내용")
                .name("미분류")
                .build();
    }

    private void relationWithMember(Member member) {
        validation(member);
        this.member = member;
        member.addBlog(this);
    }

    private void validation(Member member) {
        assert member != null;
        if(member.hasBlog())
            throw new NonUniqueBlogException();
    }

    public void createPost(String title, String content){
        postList.add(Post.builder()
                .title(title)
                .content(content)
                .blog(this)
                .build());
    }

    public void changeStatus(){
        status = status.changeStatus();
    }
}
