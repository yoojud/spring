package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Board;
import com.example.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

// 컨트롤러 => 크롬에서 주소를 입력하면 자동으로 실행되는 역할
@Controller
// 저장소 객체 생성용
@RequiredArgsConstructor

public class BoardController {

    final BoardRepository bRepository; // 저장소 객체 생성

    // http://127.0.0.1:8080/board/selectone.do
    @GetMapping(value = "/board/selectone.do")
    public String boardOne() {
        // resources/templates/boardlist1.html 표시됨.
        return "boardone";
    }

    // http://127.0.0.1:8080/board/list.do
    @GetMapping(value = "/board/list.do")
    public String boardList(Model model) {
        // DB에서 전체 내용을 가져옴
        List<Board> list = bRepository.findAll();
        // terminal 확인하기
        System.out.println(list.toString());
        // html로 DB에서 꺼낸값 전송
        model.addAttribute("list", list);
        // resources/templates/boardlist1.html 표시됨.
        return "boardlist1";
    }

    // 글쓰기 화면 http://127.0.0.1:8080/board/insert.do
    @GetMapping(value = "/board/insert.do")
    public String boardInsert() {
        return "boardwrite"; // resources/templates/boardwrite.html 표시됨.
    }

    // 사용자가 입력후 버튼 눌렀을때 안보이는 화면
    @PostMapping(value = "/board/insertaction.do")
    public String boardInsertAction(@ModelAttribute Board board) {
        board.setNo(System.currentTimeMillis()); // 중복되지 않는값
        board.setHit(1237L);
        board.setRegdate(new Date());

        System.out.println(board.toString());
        bRepository.save(board);
        // redirect가 있으면 html파일이 표시되는 것이 아니고
        // 주소창에 주소를 입력하고 엔터키 누르기
        // 목록으로 이동시킴
        return "redirect:/board/list.do";
    }

}
