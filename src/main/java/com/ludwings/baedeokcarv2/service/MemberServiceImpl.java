package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.member.MemberDeleteReqDto;
import com.ludwings.baedeokcarv2.domain.model.Member;
import com.ludwings.baedeokcarv2.domain.dto.member.MemberDto;
import com.ludwings.baedeokcarv2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public boolean isLoginIdDuplicate(MemberDto memberDto) {

        boolean ret = false;
        Optional<Member> findMember = memberRepository.findMemberByLoginId(memberDto.getLoginId());

        if (findMember.isPresent()) {
            ret = true;
        }

        return ret;
    }

    @Override
    public String findMemberById(Long memberId) {
        Optional<Member> findMember = memberRepository.findMemberById(memberId);

        if (findMember.isPresent()) {
            return findMember.get().getLoginId();
        }

        return "";
    }

    @Override
    public Member findMemberByLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findMemberByLoginId(loginId);

        if (findMember.isPresent()) {
            return findMember.get();
        }

        return null;
    }

    @Override
    public List<String> findAllMember() {
        List<Member> all = memberRepository.findAll();

        return all.stream().map(m -> m.getLoginId()).collect(Collectors.toList());
    }

    @Override
    public String login(MemberDto memberDto) {
        Member findMember = findMemberByLoginId(memberDto.getLoginId());

        if (findMember != null) {
            if (findMember.getPassword().equals(memberDto.getPassword())) {
                return findMember.getLoginId();
            }
        }

        return null;
    }

    @Transactional
    @Override
    public boolean deleteMember(MemberDeleteReqDto memberDto) {

        Member findMember = findMemberByLoginId(memberDto.getLoginId());

        if (findMember == null) {
            return false;
        }

        memberRepository.delete(findMember);
        return true;
    }

    @Override
    @Transactional
    @Modifying
    public String modifyMember(MemberDto memberDto) {

        Member findMember = findMemberByLoginId(memberDto.getLoginId());

        if (findMember == null) {
            return null;
        }

        findMember.updateMemberInfo(memberDto.toEntity());

        return findMember.getLoginId();
    }
}
