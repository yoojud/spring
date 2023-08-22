package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping(value = "/member/join.do")
    public String join() {
        return "join";
    }

    // 아이디 중복확인
    @GetMapping(value = "/member/check.do")
    public String check(){
        //
    }
    

    // 암호확인
}
