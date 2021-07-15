package com.sparta.project03.service;

import com.sparta.project03.domain.Comment;
import com.sparta.project03.dto.CommentRequestDto;
import com.sparta.project03.repository.CommentRepository;
import com.sparta.project03.utill.CommentSpecsFication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment creatComment(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public List<Comment> readComment(Long article_id) {
        return commentRepository.findAll(CommentSpecsFication.withArticle_id(article_id));
    }

    @Transactional
    public Long update_comment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지않습니다"));
        comment.update_comment(commentRequestDto);
        return comment.getId();
    }

    @Transactional
    public Long deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        commentRepository.deleteById(id);
        return comment.getId();
    }
}
