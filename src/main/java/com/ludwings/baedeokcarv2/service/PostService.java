package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.post.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    boolean writePost(PostDto postDto);

    Page<PostDto> searchPost(Pageable pageable, String keyword);

    PostDto detailPost(PostDto postDto);

    void modifyPost(PostDto postDto);

    boolean deletePost(PostDto postDto);
}
