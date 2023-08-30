/*package com.example.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    final MemberRepository mRepository;

    // 127.0.0.1:8081/member/login.do
    @GetMapping(value="/member/login.do")
    public String login() {
        return "login"; //join.html표시
    }

    // 127.0.0.1:8081/member/join.do
    @GetMapping(value="/member/join.do")
    public String join() {
        return "join"; //join.html표시
    }

    @PostMapping(value="/member/joinaction.do")
    public String joinaction(@ModelAttribute Member member, 
                        @RequestParam(name="email1") String email1,
                        @RequestParam(name="email2") String email2) {
        // 입력값 받기
        member.setRegdate( new Date() );
        member.setEmail(email1 + "@" + email2);
        System.out.println(member.toString());

        mRepository.save(member);
  
        // 페이지이동
        return "redirect:/member/login.do";
    }



    @PostMapping(value="/member/loginaction.do")
    public String loginaction(@ModelAttribute Member member) {
        // 1. 입력값 받기
        System.out.println(member.toString());

        // 2. 아이디를 이용해서 회원정보 가져오기
        Member member1 = mRepository.findById(member.getEmail()).orElse(null);

        // 3. 암호가 일치하는지 확인
        if(member1.getPassword().equals(member.getPassword())) {
            //4. 로그인 성공
            return "redirect:/board/list.do";    
        }
        // 5. 로그인 실패
        return "redirect:/member/login.do";
    }

}*/