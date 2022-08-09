package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.fixture.BlogCreateFixture;
import com.blog.blogservice.fixture.MemberCreatorFixture;
import com.blog.blogservice.fixture.MemberFixture;
import com.blog.blogservice.service.BlogService;
import com.blog.blogservice.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.MultiValueMap;

@SpringBootTest
public class BlogRepositoryImplTest {

    @Autowired
    BlogService blogService;
    @Autowired
    MemberService memberService;

    @BeforeEach
    void initFixture(){
        Long memberId = memberService.join(MemberCreatorFixture.memberCreatorFixture());

        BlogCreate blogCreate = BlogCreate.builder()
                .title("test")
                .tag("test")
                .build();

        Long blogId = blogService.create(memberId, blogCreate);


    }
}
