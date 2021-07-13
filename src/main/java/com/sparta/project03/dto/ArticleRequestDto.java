package com.sparta.project03.dto;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private String username;
    private String title;
    private String contents;
    private Long userId;
}
