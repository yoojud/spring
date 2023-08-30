package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.Board;

// 실제로 DB연동 부분
// MongoRepository<게시판엔티티, 기본키의타입>
public interface BoardRepository extends MongoRepository<Board, Long> {
    
    //전체 개수 구하기 <=> 검색어가 포함되어 있는 항목의 개수를 구함.
    long countByTitleContaining(String title);
    
    // ex) 가 => 가나다(o) 나가다(o) 다다다(x)
    // 제목을 검색하는 기능임
    List<Board> findByTitleContaining(String title);

    // 제목검색기능 + 글번호내림차순정렬
    List<Board> findByTitleContainingOrderByNoDesc(String title);

    // 제목검색기능 + 정렬 + 페이지네이션
    List<Board> findByTitleContainingOrderByNoDesc(String title, Pageable pageable);

        //이전글
    @Aggregation(pipeline = {
        "{ '$match'   : { '_id' : { $lt : ?0 } } }",
        "{ '$project' : { '_id' :  1 }           }",
        "{ '$sort'    : { '_id' :  -1 }           }",
        "{ '$limit'   : 1                        }"
    })
    Long selectBoardPrev(long no);

    //다음글
    @Aggregation(pipeline = {
        "{ '$match'   : { '_id' : { $gt : ?0 } } }",
        "{ '$project' : { '_id' :  1 }           }",
        "{ '$sort'    : { '_id' :  1 }           }",
        "{ '$limit'   : 1                        }"
    })
    Long selectBoardNext(long no);

    Page<Board> findByTitleContainingOrContentContainingOrderByNoDesc(String txt, String txt2, PageRequest pageRequest);
}
