package com.blog.blogservice.processor.interceptor;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.domain.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.blog.blogservice.domain.RoleType.OWNER;

@Data
@NoArgsConstructor
public class UserDetails {

    @NotNull
    private Long memberId;
    private String name;
    private RoleType role;
    private Long blogId;

    public UserDetails(Member member) {
        memberId = member.getId();
        name = member.getName();
        role = member.getRoleType();
        if (role==OWNER)
            blogId = member.getBlog().getId();
        else
            blogId = 0L;
    }
}
