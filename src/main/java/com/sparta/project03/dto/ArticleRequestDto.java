package com.sparta.project03.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleRequestDto {
    private String username;
    private String title;
    private String contents;
    private String userId;
}
