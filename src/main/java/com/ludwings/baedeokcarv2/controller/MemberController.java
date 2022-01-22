package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.member.MemberDeleteReqDto;
import com.ludwings.baedeokcarv2.domain.model.Member;
import com.ludwings.baedeokcarv2.domain.dto.member.MemberDto;
import com.ludwings.baedeokcarv2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public String join(@ModelAttribute MemberDto memberDto) {
        String loginId = memberService.join(memberDto);

        return "redirect:/";
    }

    // 멤버 id로 1건 조회
    @GetMapping("/member/{loginId}")
    public @ResponseBody
    MemberDto findOneByLoginId(@PathVariable String loginId) {
        Member findMember = memberService.findMemberByLoginId(loginId);

        if (findMember != null) {
            return new MemberDto(findMember);
        }

        return null;
    }

    // 로그인
    @GetMapping("/login")
    public @ResponseBody
    String login(@ModelAttribute MemberDto memberDto, HttpSession session) {
        String loginId = memberService.login(memberDto);
        session.setAttribute("loginId", loginId);

        return loginId;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }

    @PostMapping("/idCheck.do")
    @ResponseBody
    public Map<String, String> idCheck(@ModelAttribute MemberDto memberDto) {
        log.info(memberDto.getLoginId());
        Map<String, String> ret = new HashMap<String, String>();
        boolean isLoginIdDuplicated = memberService.isLoginIdDuplicate(memberDto);

        ret.put("canUse", "" + !isLoginIdDuplicated);

        return ret;
    }

    @PatchMapping("/member/{loginId}")
    public String modifyMemberInfo(@ModelAttribute MemberDto memberDto) {

        memberService.modifyMember(memberDto);

        return "redirect:/";
    }

    @DeleteMapping("/member/{loginId}")
    public String deleteMemberInfo(@ModelAttribute MemberDeleteReqDto reqDto) {

        memberService.deleteMember(reqDto);

        return "redirect:/";
    }
}
