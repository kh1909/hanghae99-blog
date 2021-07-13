package com.sparta.project03.domain;

import com.sparta.project03.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long article_id;

    @Column(length = 1000, nullable = false)
    private String comment;

    @Column(nullable = true)
    private String username;

    public Comment(CommentRequestDto requestDto) {
        this.article_id = requestDto.getArticle_id();
        this.username = requestDto.getUsername();
        this.comment = requestDto.getComment();
    }

    public void update_comment(CommentRequestDto requestDto) {
        this.article_id = requestDto.getArticle_id();
        this.username = requestDto.getUsername();
        this.comment = requestDto.getComment();
    }
}
