package com.blog.blogservice.repository;

import com.blog.blogservice.domain.Post;
import com.blog.blogservice.repository.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
