package com.ludwings.baedeokcarv2.repository;

import com.ludwings.baedeokcarv2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByLoginId(String loginId);
    Optional<Member> findMemberById(Long id);
}
