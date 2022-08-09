package com.blog.blogservice.domain;

import com.blog.blogservice.repository.BlogRepository;
import com.blog.blogservice.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogTest {

    @Autowired
    MemberService memberService;
    @Autowired
    BlogRepository blogRepository;

    @Test
    @DisplayName("카테고리 생성 테스트")
    void test() {
        //given
//
//        Long join = memberService.join(MemberCreator.builder()
//                .name("test")
//                .password("test")
//                .loginId("test")
//                .build());
//
//        Member fixtureMember = memberService.findById(join);
//        Blog blog = Blog.createBlog(fixtureMember, "test", "test");
//        blogRepository.save(blog);
//        System.out.println(blog.getCategories().get(0));
//        //when

        //then
    }

}