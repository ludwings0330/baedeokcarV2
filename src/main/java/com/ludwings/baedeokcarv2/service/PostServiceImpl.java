package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.Member;
import com.ludwings.baedeokcarv2.domain.Post;
import com.ludwings.baedeokcarv2.domain.dto.PostDto;
import com.ludwings.baedeokcarv2.repository.MemberRepository;
import com.ludwings.baedeokcarv2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public boolean writePost(PostDto postDto) {
        Post post = dtoToEntity(postDto);
        postRepository.save(post);

        return true;
    }

    @Override
    /*
    Pageable 3개 핵심, page, size, sort
     */
    public Page<PostDto> searchPost(Pageable pageable, String keyword) {
        Page<Post> posts;
        if (keyword.equals("")) {
            posts = postRepository.findAll(pageable);
        } else {
            posts = postRepository.findAllByOption(keyword, pageable);
        }

        return posts.map(p -> new PostDto(p));
    }

    @Override
    @Transactional
    public PostDto detailPost(PostDto postDto) {
        Optional<Post> findPost = postRepository.findById(postDto.getId());

        findPost.get().hitPost();

        return new PostDto(findPost.get());
    }

    @Override
    @Transactional
    public void modifyPost(PostDto postDto) {
        Optional<Post> findPost = postRepository.findById(postDto.getId());
        Post post = findPost.get();

        post.modifyInfo(postDto.getTitle(), postDto.getContent());
    }

    @Override
    @Transactional
    public boolean deletePost(PostDto postDto) {
        Optional<Post> findPost = postRepository.findById(postDto.getId());
        postRepository.delete(findPost.get());

        return true;
    }

    private Post dtoToEntity(PostDto postDto) {
        Optional<Member> findMember = memberRepository.findMemberByLoginId(postDto.getWriter());

        return Post.builder()
                .content(postDto.getContent())
                .title(postDto.getTitle())
                .writer(findMember.get())
                .build();
    }
}
