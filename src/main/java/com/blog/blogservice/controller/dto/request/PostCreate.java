package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostCreate {

    private Long blogId;
    private String title;
    private String content;

    @Builder
    public PostCreate(Long blogId, String title, String content) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
    }
}
