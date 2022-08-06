package com.blog.blogservice.repository;

import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.exception.NonUniqueBlogException;
import com.blog.blogservice.repository.entityrepo.BlogRepository;
import com.blog.blogservice.repository.entityrepo.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BlogRepository blogRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Blog 엔티티 생성시 연관관계 매핑 테스트")
    void new_blog() {
        Member member = getTestMember();
        Blog testBlog = getTestBlog(member);

        assertThat(testBlog.getMember()).isEqualTo(member);
        assertThat(member.getBlog()).isEqualTo(testBlog);
    }

    private Blog getTestBlog(Member member) {
        return Blog.createBlog(member, "test title", "test");
    }

    private Member getTestMember() {
        return Member.builder()
                .name("tester")
                .password("test")
                .loginId("test")
                .build();

    }

    @Test
    @DisplayName("[negative] - 회원은 하나의 블로그만 가질 수 있다.")
    void member_only_one_blog() {
        Member testMember = getTestMember();
        Blog testBlog = getTestBlog(testMember);

        NonUniqueBlogException e = assertThrows(NonUniqueBlogException.class,
                () -> Blog.createBlog(testMember, "예외 발생", "예외 발생"));
        assertThat(e.getMessage()).isEqualTo("블로그는 하나만 생성할 수 있습니다.");
    }

    @Test
    @DisplayName("[negative] - 블로그는 회원 객체 없이 생성이 불가능하다.")
    void blog_negative() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Blog.createBlog(null, "null", "null"));
        assertThat(e.getMessage()).isEqualTo("member must not be null");
    }

    @Test
    @DisplayName("Blog CRUD")
    @Transactional
    void create_blog() {
        Member savedMember = memberRepository.save(getTestMember());
        Blog testBlog = getTestBlog(savedMember);
        blogRepository.save(testBlog);

        Blog blog = blogRepository.findById(testBlog.getId())
                .orElse(null);

        em.flush();
        em.clear();

        assertThat(blog.getId()).isEqualTo(testBlog.getId());

        blogRepository.deleteById(testBlog.getId());

        assertThrows(EmptyResultDataAccessException.class,
                () -> blogRepository.deleteById(testBlog.getId()));
    }


}