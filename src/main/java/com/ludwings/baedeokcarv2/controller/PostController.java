package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.PostDto;
import com.ludwings.baedeokcarv2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String searchPost(Model model,
                             @PageableDefault(size = 20, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam(defaultValue="") String keyword) {
        Page<PostDto> page = postService.searchPost(pageable, keyword);

        model.addAttribute("page", page);
        model.addAttribute("posts", page.getContent());

        return "index";
    }

    @PostMapping("/post")
    public String savePost(@ModelAttribute PostDto postDto) {
        postService.writePost(postDto);

        return "index";
    }

    @GetMapping("/post/{postId}")
    public String viewPost(Model model, @PathVariable Long postId) {
        PostDto findPost = postService.detailPost(PostDto.builder().id(postId).build());
        model.addAttribute("post", findPost);

        return "index";
    }

    @PatchMapping("/post/{postId}")
    public String modifyPost(@PathVariable Long postId, @ModelAttribute PostDto postDto) {
        postDto.setId(postId);

        return "index";
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable Long postId) {
        return "index";
    }
}
