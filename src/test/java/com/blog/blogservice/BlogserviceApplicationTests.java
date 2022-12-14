package com.blog.blogservice;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import com.blog.blogservice.controller.dto.request.MemberCreator;
import com.blog.blogservice.controller.dto.request.PostCreate;
import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.domain.Status;
import com.blog.blogservice.service.BlogService;
import com.blog.blogservice.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.blog.blogservice.domain.Status.*;
import static com.blog.blogservice.domain.Status.OPERATION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BlogserviceApplicationTests {

	@Autowired
	MemberService memberService;
	@Autowired
	BlogService blogService;

	//총 결과
	/**
	 * 0 )member save시 insert
	 * O )select member - 블로그 생성 시 참조할 회원 find
	 * X )inert Blog 전 Select Blog where member_id 쿼리가 나가면 안됨
	 * 0 )insert Blog - 블로그 생성 후 저장
	 * 0 )category - insert 나가는건 맞음 (아직 테스트 필요)
	 * 0 )update 쿼리 - 블로그 생성 후 저장  User의 Role은 USER -> OWNER로 변경
	 * 0 ) Post 작성 시 자신이 속할 Blog를 Id로 찾고 BLog Entity 내부에서 Cascade를 이용하여 save됨
	 * ㄴ> select Blog & inert Post
	 */

	@Test
	void contextLoads() {

	}

	@Test
	@DisplayName("Blog DB로 부터 생성 테스트")
	void test() {

        Member byId = memberService.findById(1L);
        System.out.println("byId.getName() = " + byId.getName());
        System.out.println("byId.getBlog().getId() = " + byId.getBlog().getId());
        System.out.println("byId.getBlog().getId() = " + byId.getBlog().getTitle());

    }

	@Test
	@Transactional
	void Blog_status_change_test() {
	}
}
