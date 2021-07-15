package com.sparta.project03.controller;

import com.sparta.project03.domain.Comment;
import com.sparta.project03.dto.CommentRequestDto;
import com.sparta.project03.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public Comment creatComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.creatComment(requestDto);
        return comment;
    }

    @GetMapping("/api/comments/{article_id}")
    public List<Comment> readComment(@PathVariable Long article_id ){
        return commentService.readComment(article_id);
    }

    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.update_comment(id, commentRequestDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
