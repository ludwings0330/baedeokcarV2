package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.Member;
import com.ludwings.baedeokcarv2.domain.dto.MemberDto;
import com.ludwings.baedeokcarv2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public @ResponseBody String join(@ModelAttribute MemberDto memberDto) {
        String loginId = memberService.join(memberDto);

        return loginId;
    }

    // 멤버 id로 1건 조회
    @GetMapping("/member/{loginId}")
    public @ResponseBody MemberDto findOneByLoginId(@PathVariable String loginId) {
        Member findMember = memberService.findMemberByLoginId(loginId);

        if (findMember != null) {
            return new MemberDto(findMember);
        }

        return null;
    }
    // 로그인
    @GetMapping("/login")
    public @ResponseBody String login(@ModelAttribute MemberDto memberDto, HttpSession session) {
        String loginId = memberService.login(memberDto);

        if (loginId == null) {
            return "fail";
        } else {
            session.setAttribute("authInfo", loginId);
            return loginId;
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public @ResponseBody String logout(HttpSession session) {
        String authInfo = (String) session.getAttribute("authInfo");
        session.invalidate();
        return authInfo;
    }
}
