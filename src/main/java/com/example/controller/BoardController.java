package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Board;
import com.example.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

// 컨트롤러 => 크롬에서 주소를 입력하면 자동으로 실행되는 역할
@Controller
@RequiredArgsConstructor // 저장소 객체 생성용

public class BoardController {

    final BoardRepository bRepository; // 저장소 객체 생성

    // 글내용 변경 => http://127.0.0.1:8080 /board/updateaction.do
    @PostMapping(value = "/board/updateaction.do")
    public String boardUpdateAction(@ModelAttribute Board board) {
        // 1. 입력한 내용 확인
        System.out.println(board.toString());

        // 2. DB에서 정보 변경( 기존데이터 읽기 )
        // board1은 DB에서 꺼낸 기존 데이터
        // board는 사용자가 입력한 내용
        Board board1 = bRepository.findById(board.getNo()).orElse(null);
        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent());
        board1.setWriter(board.getWriter());
        bRepository.save(board1);

        // 3. 성공페이지로 이동
        return "redirect:/board/list.do"; // 주소창의 /board/list.do로 변경후 엔터키를 수행
    }

    // 게시글변경화면 => http://127.0.0.1:8080 /board/update.do ?no=1692603694745
    @GetMapping(value = "/board/update.do")
    public String boardUpdate(@RequestParam(name = "no") long no, Model model) {
        // 1. 글번호를 이용해서 데이터읽기
        Board brd = bRepository.findById(no).orElse(null);

        // 2. html 전달
        model.addAttribute("board", brd);

        // 3. html 표시
        return "boardupdate";
    }

    // 게시글 삭제 => http://127.0.0.1:8081 /board/delete.do ?no=1692603484652
    @GetMapping(value = "/board/delete.do")
    public String boardDelete(@RequestParam(name = "no") long no) {
        System.out.println(no);

        // DB에 삭제하기
        bRepository.deleteById(no);

        // 주소를 변경시켜서 목록으로 이동
        return "redirect:/board/list.do";
    }

    // http://127.0.0.1:8081/board/selectone.do?no=1692599932160
    @GetMapping(value = "/board/selectone.do")
    public String boardOne(@RequestParam(name = "no") long no, Model model) {
        System.out.println(no);

        // 1. DB에서 정보를 꺼내야 됨
        Board brd = bRepository.findById(no).orElse(null);
        System.out.println(brd);

        // 2. html전송
        model.addAttribute("board", brd);
        model.addAttribute("title", "게시판상세");

        // 3. html표시
        return "boardone"; // boardone.html
    }

    // http://127.0.0.1:8080/board/list.do?txt=
    // http://127.0.0.1:8080/board/list.do ?txt=검색어
    @GetMapping(value = "/board/list.do")
    public String boardList(Model model,
            @RequestParam(name = "txt", defaultValue = "") String txt,
            @RequestParam(name = "page", defaultValue = "0") int page) {
        // 주소창에 list.do만 입력하면 자동으로 list.do?page=1로 바꿈
        if (page == 0) {
            return "redirect:/board/list.do?page=1";
        }

        // (페이지번호0번부터, 개수)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 1. DB에서 전체 내용을 가져옴.
        List<Board> list = bRepository.findByTitleContainingOrderByNoDesc(txt, pageRequest);
        long total = bRepository.countByTitleContaining(txt);

        // 2. terminal에 확인하기
        System.out.println(list.toString());

        // 3. html로 db에서 꺼낸값 전송
        model.addAttribute("list", list);

        // ex) 10 => 1
        // ex) 11 => 2
        // ex) 21 => 3
        // ex) 50 => 5
        model.addAttribute("pages", (total - 1) / 10 + 1);

        // 4. resources/templates/boardlist1.html 표시됨.
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

        return "redirect:/board/list.do";
    }
}