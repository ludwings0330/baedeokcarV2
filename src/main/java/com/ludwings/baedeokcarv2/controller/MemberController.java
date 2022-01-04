package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.MemberDto;
import com.ludwings.baedeokcarv2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public @ResponseBody String join(@ModelAttribute MemberDto memberDto) {
        String loginId = memberService.join(memberDto);

        return loginId;
    }
}
