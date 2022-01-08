package com.ludwings.baedeokcarv2.domain.dto;

import com.ludwings.baedeokcarv2.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String content;

    private String writer;


    public PostDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
