package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.member.MemberDeleteReqDto;
import com.ludwings.baedeokcarv2.domain.model.Member;
import com.ludwings.baedeokcarv2.domain.dto.member.MemberDto;

import java.util.List;

public interface MemberService {
    String join(MemberDto memberDto);
    boolean isLoginIdDuplicate(MemberDto memberDto);
    String findMemberById(Long memberId);
    Member findMemberByLoginId(String loginId);
    List<String> findAllMember();
    String login(MemberDto memberDto);
    boolean deleteMember(MemberDeleteReqDto memberDto);
    String modifyMember(MemberDto memberDto);
}
