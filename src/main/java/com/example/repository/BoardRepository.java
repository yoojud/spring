package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.Board;

// 실제로 DB연동 부분
// MongoRepository<게시판엔티티, 기본키의타입>
public interface BoardRepository extends MongoRepository<Board, Long> {
    
    // 전체개수구하기 -> 검새거가 포함되어있는 항목의 개수를 구함
    long countByTitleContaining(String title);

    // ex) 가 => 가나다(o) 나가다(o) 다다다(x)
    // 제목을 검색하는 기능임
    List<Board> findByTitleContaining(String title);

    // 제목검색기능 + 글번호내림차순정렬
    List<Board> findByTitleContainingOrderByNoDesc(String title);

    // 제목검색기능 + 정렬 + 페이지네이션
    List<Board> findByTitleContainingOrderByNoDesc(String title, Pageable pageable);
}
