package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

import static com.blog.blogservice.processor.config.ViewNameConfig.INSERT_BLOG;

@Data
@NoArgsConstructor
public class BlogCreator {

    @NotEmpty
    private Long memberId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String tag;
    private final String exOccursView = INSERT_BLOG;

    @Builder
    public BlogCreator(String title, String tag) {
        this.title = title;
        this.tag = tag;
    }
}
