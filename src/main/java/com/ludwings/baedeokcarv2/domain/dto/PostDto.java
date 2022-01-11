package com.ludwings.baedeokcarv2.domain.dto;

import com.ludwings.baedeokcarv2.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String content;

    private String writer;

    LocalDateTime createDate;
    LocalDateTime modifiedDate;

    int hitCount;
    public PostDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter().getName();
        this.createDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.hitCount = entity.getHitCount();
    }
}
