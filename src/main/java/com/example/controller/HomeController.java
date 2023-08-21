package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러 => 크롬에서 주소를 입력하면 자동으로 수행되는 클래스
@Controller
public class HomeController {
    
    // 127.0.0.1:8080/home.do
    @GetMapping(value = "/home.do")
    public String home() {
        return "home";// resources/templates/home.html을 표시
    }

    // 127.0.0.1:8080/main.do
    @GetMapping(value = "/main.do")
    public String main() {
        return "main"; // resources/templates/main.html을 표시
    }

}
