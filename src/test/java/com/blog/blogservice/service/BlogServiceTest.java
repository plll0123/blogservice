//package com.blog.blogservice.service;
//
//import com.blog.blogservice.controller.dto.request.BlogCreate;
//import com.blog.blogservice.controller.dto.request.MemberCreator;
//import com.blog.blogservice.domain.Blog;
//import com.blog.blogservice.domain.Member;
//import com.blog.blogservice.domain.RoleType;
//import com.blog.blogservice.repository.BlogRepository;
//import com.blog.blogservice.repository.MemberRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.*;
//
//import static com.blog.blogservice.domain.Blog.createBlog;
//import static com.blog.blogservice.domain.RoleType.OWNER;
//import static com.blog.blogservice.domain.RoleType.USER;
//import static com.blog.blogservice.fixture.BlogCreateFixture.blogDtoFixture;
//import static com.blog.blogservice.fixture.MemberCreatorFixture.memberCreatorFixture;
//import static com.blog.blogservice.fixture.MemberFixture.memberFixture;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class BlogServiceTest {
//
//    @Autowired
//    BlogService blogService;
//    @Autowired
//    BlogRepository blogRepository;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    MemberService memberService;
//    @PersistenceContext
//    EntityManager em;
//
//    @AfterEach
//    void clearFixture(){
//        memberRepository.deleteAll();
//    }
//
//    private BlogCreate blogDtoFixture(){
//        return BlogCreate
//                .builder()
//                .tag("test")
//                .title("test")
//                .build();
//    }
//
//    @Test
//    @DisplayName("블로그 생성 테스트")
//    void create_blog() {
//
//        Member member = memberRepository.save(memberFixture());
//        Long blogId = blogService.create(member.getId(), blogDtoFixture());
//
//        Blog blog = blogRepository.findForWritePost(blogId)
//                .orElse(null);
//
//        assertThat(blog).extracting("title", "tag", "id")
//                .containsExactly("test", "test", blogId);
//
//        assert blog != null;
//        assertThat(blog.getMember()).extracting("id", "name", "password", "loginId")
//                .containsExactly(member.getId(), member.getName(), member.getPassword(), member.getLoginId());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("블로그 생성 시 Member의 RoleType이 DB에 OWNER로 반영돼야 한다.")
//    void testBlogService() {
//
//        MemberCreator memberCreatorFixture = memberCreatorFixture();
//
//        Long assertMemberId = memberService.join(memberCreatorFixture);
//
//        Member assertMember = memberService.findById(assertMemberId);
//
//        assertThat(assertMember.getRoleType()).isEqualTo(USER);
//
//        Long blogId = blogService.create(assertMemberId, blogDtoFixture());
//
//        em.flush();
//        em.clear();
//
//        Blog blog = blogRepository.findForWritePost(blogId)
//                .orElse(null);
//
//        Member blogUser = blog.getMember();
//
//        assertThat(blogUser.getRoleType()).isEqualTo(OWNER);
//
//        Member member = memberRepository.findById(assertMember.getId())
//                .orElse(null);
//
//        assertThat(member.getRoleType()).isEqualTo(OWNER);
//        assertThat(member).isEqualTo(blogUser);
//
//    }
//
//    @Test
//    @DisplayName("[negative] - 블로그 저장 실패 시 Member의 RoleType은 OWNER에서 USER로 롤백돼야 한다.")
//    void negative_blog_save() {
//
//        Long assertMemberId = memberService.join(memberCreatorFixture());
//
//        Member assertMember = memberService.findById(assertMemberId);
//
//        assertThat(assertMember.getRoleType()).isEqualTo(USER);
//
//
//        Assertions.assertThrows(RuntimeException.class,
//                () -> create(assertMemberId, blogDtoFixture()));
//
//
//        Member rollbackMember = memberService.findById(assertMemberId);
//        assertThat(rollbackMember.getRoleType()).isEqualTo(USER);
//
//    }
//
//    @Transactional
//    public Long create(Long memberId, BlogCreate blogCreate){
//
//        Member findMember = memberService.findById(memberId);
//
//        blogRepository.save(createNewBlog(findMember, blogCreate))
//                .getId();
//
//        assertThat(findMember.getRoleType()).isEqualTo(OWNER);
//        throw new RuntimeException();
//    }
//
//    public Blog createNewBlog(Member member, BlogCreate blogCreate) {
//        return createBlog(member, blogCreate.getTitle(), blogCreate.getTag());
//    }
//}