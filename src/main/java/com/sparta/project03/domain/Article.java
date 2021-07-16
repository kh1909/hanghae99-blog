package com.sparta.project03.domain;

import com.sparta.project03.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String contents;



    public Article(ArticleRequestDto requestDto, String userName) {
        this.username = userName;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(ArticleRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}