package com.ludwings.baedeokcarv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/posts")
    public String getAllPosts() {
        return "post/board";
    }

    @GetMapping("/posts-form")
    public String postForm() {
        return "";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "join";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/carRegister-form")
    public String carRegisterForm() {
        return "";
    }
}
