package com.ongshop.controller;

import com.ongshop.dto.MemberDto;
import com.ongshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signin")
    public String loginForm() {
        return "member/signin";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDto memberDto) {
        memberService.signup(memberDto);

        return "redirect:/signin";
    }
}
