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
    public PostDto detailPost(PostDto postDto) {
        return null;
    }

    @Override
    public PostDto modifyPost(PostDto postDto) {
        return null;
    }

    @Override
    public boolean deletePost(PostDto postDto) {
        return false;
    }

    @Override
    public int hitPost(Post post) {
        return 0;
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
