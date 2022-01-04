package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.Member;
import com.ludwings.baedeokcarv2.domain.dto.MemberDto;
import com.ludwings.baedeokcarv2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public String join(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        Member savedMember = memberRepository.save(member);
        return savedMember.getLoginId();
    }
}
