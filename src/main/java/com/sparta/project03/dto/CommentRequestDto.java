package com.sparta.project03.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    private Long article_id;
    private String username;
    private String comment;
}

