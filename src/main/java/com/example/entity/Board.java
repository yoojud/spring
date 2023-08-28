package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 엔티티 => 저장하고 싶은 데이터의 구조를 가지는 클래스
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "boards")

public class Board {

    @Id
    private long no;

    private String title;
    private String content;
    private String writer;
    private long hit = 1;
    private Date regdate = new Date();
    public static int lastIndex=1;
}
