package com.ludwings.baedeokcarv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/post-form")
    public String postForm() {
        return "post/post-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "join";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/car-register-form")
    public String carRegisterForm() {
        return "car/car-register-form";
    }
}
