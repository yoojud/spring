package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    //  http://127.0.0.1:8080/member/join.do
    @GetMapping(value = "/member/join.do")
    public String join() {
        return "join";
    }

    
    

    // 암호확인
}
