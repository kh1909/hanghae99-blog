package com.sparta.project03.service;

import com.sparta.project03.domain.Comment;
import com.sparta.project03.dto.CommentRequestDto;
import com.sparta.project03.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long update_comment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지않습니다")
        );
        comment.update_comment(commentRequestDto);
        return comment.getId();
    }
}
