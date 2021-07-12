package com.sparta.project03.controller;

import com.sparta.project03.domain.Comment;
import com.sparta.project03.dto.CommentRequestDto;
import com.sparta.project03.repository.CommentRepository;
import com.sparta.project03.service.CommentService;
import com.sparta.project03.utill.CommentSpecsFication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/api/comments")
    public Comment creatComment(@RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = new Comment(commentRequestDto);
        return commentRepository.save(comment);
    }

    @GetMapping("/api/comments/{article_id}")
    public List<Comment> getComment(@PathVariable Long article_id){
        return commentRepository.findAll(CommentSpecsFication.withArticle_id(article_id));
    }

    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.update_comment(id, commentRequestDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
