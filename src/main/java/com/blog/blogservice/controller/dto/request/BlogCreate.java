package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

import static com.blog.blogservice.processor.config.ViewNameConfig.INSERT_BLOG;

@Data
public class BlogCreate {

    @NotEmpty
    private String title;
    private String tag;
    private final String exOccursView = INSERT_BLOG;

    @Builder
    public BlogCreate(String title, String tag) {
        this.title = title;
        this.tag = tag;
    }
}
