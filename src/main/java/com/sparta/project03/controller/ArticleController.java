package com.sparta.project03.controller;

import com.sparta.project03.domain.Article;
import com.sparta.project03.dto.ArticleRequestDto;
import com.sparta.project03.repository.ArticleRepository;
import com.sparta.project03.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService; //레포지토리 서비스 분리 디티오 서비스 컨트롤러 역활 분리

    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto) {
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    @GetMapping("/api/articles")
    public List<Article> readArticle() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/detail/{id}")
    public Article readDetail(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }
}
