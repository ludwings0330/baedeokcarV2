package com.ludwings.baedeokcarv2.repository;

import com.ludwings.baedeokcarv2.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    @Query("select p from Post p where p.title LIKE %:keyword% OR p.content LIKE %:keyword% OR p.writer.loginId LIKE %:keyword%")
    Page<Post> findAllByOption(String keyword, Pageable pageable);
}
