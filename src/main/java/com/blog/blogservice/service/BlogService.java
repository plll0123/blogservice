package com.blog.blogservice.service;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.controller.dto.response.BlogMainResponse;
import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.exception.BlogNotFoundException;
import com.blog.blogservice.repository.BlogRepository;
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
    private final PostService postService;
//    private final CategoryService categoryService;

    private void validationCheck(Long blogId) {
        if (blogId == null) {
            throw new BlogNotFoundException();
        }
    }

    @Transactional
    public Long create(Long memberId, BlogCreate blogCreate) {

        Member findMember = memberService.findById(memberId);

        return blogRepository.save(createNewBlog(findMember, blogCreate))
                .getId();
    }

    private Blog createNewBlog(Member member, BlogCreate blogCreate) {
        return createBlog(member, blogCreate.getTitle(), blogCreate.getTag());
    }

    public BlogMainResponse findBlog(Long blogId) {
        validationCheck(blogId);
        return getBlogMain(blogId);
    }

    private BlogMainResponse getBlogMain(Long blogId) {
//        BlogMainResponse blogMain = blogRepository.searchBy(blogId);
//        blogMain.setPosts(postService.getPosts(blogId));
        return null;
    }
}
