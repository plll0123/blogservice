package com.blog.blogservice.service;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.controller.dto.request.PostCreate;
import com.blog.blogservice.controller.dto.response.BlogMainResponse;
import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.exception.BlogNotFoundException;
import com.blog.blogservice.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.blog.blogservice.domain.Blog.createBlog;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final MemberService memberService;
    private final BlogRepository blogRepository;

    public void isValidId(Long blogId){
        if(blogId == null){
            throw new BlogNotFoundException();
        }
    }
    public Blog find(Long blogId) {
        isValidId(blogId);
        return blogRepository.findById(blogId)
                .orElseThrow(BlogNotFoundException::new);
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


    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @Transactional
    public void changeStatus(Long blogId) {
        blogRepository.findById(blogId)
                .orElseThrow(BlogNotFoundException::new)
                .changeStatus();
    }

    public BlogMainResponse getBlogMain(Long blogId, BlogCreate blogCreate) {
        BlogMainResponse blogMain = blogRepository.searchBy(blogId);
        blogMain.setPosts(blogRepository.searchPostsBy(blogId));
        return blogMain;
    }

    @Transactional
    public void writePost(Long blogId, PostCreate postCreate){
        find(blogId)
                .createPost(postCreate.getTitle(), postCreate.getContent());
    }
}
