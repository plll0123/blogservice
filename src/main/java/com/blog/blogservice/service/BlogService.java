package com.blog.blogservice.service;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.repository.entityrepo.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.blog.blogservice.domain.Blog.createBlog;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogService {

    private final MemberService memberService;
    private final BlogRepository blogRepository;

    @Transactional
    public Long create(Long memberId, BlogCreate blogCreate){

        Member findMember = memberService.findById(memberId);

        return blogRepository.save(createNewBlog(findMember, blogCreate))
                .getId();
    }

    public Blog createNewBlog(Member member, BlogCreate blogCreate) {
        return createBlog(member, blogCreate.getTitle(), blogCreate.getTag());
    }

}
