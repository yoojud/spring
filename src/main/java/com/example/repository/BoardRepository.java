package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.entity.Board;

// 실제로 DB연동 부분
// MongoRepository<게시판엔티티, 기본키의타입>
public interface BoardRepository extends MongoRepository<Board, Long> {

}
