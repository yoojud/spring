package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping(value = "/member/join.do")
    public String join() {
        return "join";
    }

    
    

    // 암호확인
}
