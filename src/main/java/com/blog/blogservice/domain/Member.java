package com.blog.blogservice.domain;

import com.blog.blogservice.exception.IdOrPwMisMatch;
import com.blog.blogservice.exception.NonUniqueBlogException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.blog.blogservice.domain.RoleType.OWNER;
import static com.blog.blogservice.domain.RoleType.USER;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"loginId"})})
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String loginId;
    private String password;
    @Enumerated(STRING)
    private RoleType roleType;

    @OneToOne(mappedBy = "member", fetch = LAZY)
    private Blog blog;

    @Builder
    public Member(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    @PrePersist
    public void isNew() {
        if(id==null){
            roleType = USER;
        }
    }

    public void addBlog(Blog blog) {
        if(hasBlog()){
            throw new NonUniqueBlogException();
        }
        this.blog = blog;
        roleType = OWNER;
    }

    public void validationPwd(String password){
        if(!this.password.equals(password))
            throw new IdOrPwMisMatch();
    }

    public boolean hasBlog() {
        return blog != null;
    }


}
