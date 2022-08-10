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

import static com.blog.blogservice.domain.Blog.createBlog;

@Service
//@Transactional(readOnly = true)
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

        // 저장 전 select Blog ----- 가 나가는 이유 찾아야함.
        return blogRepository.save(createNewBlog(findMember, blogCreate))
                .getId();
    }

    private Blog createNewBlog(Member member, BlogCreate blogCreate) {
        return createBlog(member, blogCreate.getTitle(), blogCreate.getTag());
    }

    public Blog find(Long blogId) {
        validationCheck(blogId);
        return blogRepository.findById(blogId)
                .get();
//        return getBlogMain(blogId);
    }


    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @Transactional
    public void writePost(Long blogId, PostCreate postCreate){
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(BlogNotFoundException::new);

        blog.createPost(postCreate.getTitle(), postCreate.getContent());
    }

    @Transactional
    public void changeStatus(Long blogId){
        blogRepository.findById(blogId)
                .orElseThrow(BlogNotFoundException::new)
                .changeStatus();
    }

    private BlogMainResponse getBlogMain(Long blogId) {
//        BlogMainResponse blogMain = blogRepository.searchBy(blogId);
//        blogMain.setPosts(postService.getPosts(blogId));
        return null;
    }
}
