package com.blog.blogservice.domain;

import com.blog.blogservice.exception.NonUniqueBlogException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.mysema.commons.lang.Assert.notNull;
import static javax.persistence.CascadeType.ALL;
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

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "blog", cascade = ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder
    private Blog(Member member, String title, String tag){
        relationWithMember(member);
        this.title = title;
        this.tag = tag;
    }

    //직접적으로 instance method를 호출하진 않지만
    //간접적으로 의존하는 것이 이상하진 않은지
    public static Blog createBlog(Member member, String title, String tag){
        return Blog.builder()
                .member(member)
                .title(title)
                .tag(tag)
                .build();
    }

    private void relationWithMember(Member member) {
        validation(member);
        this.member = member;
        member.addBlog(this);
    }

    private void validation(Member member) {
        notNull(member, "member most not null");
        if(member.hasBlog())
            throw new NonUniqueBlogException();
    }

    public Post createPost(){
        return Post.builder()
                .blog(this)
                .build();
    }
}
