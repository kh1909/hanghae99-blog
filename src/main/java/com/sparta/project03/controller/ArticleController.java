package com.sparta.project03.controller;

import com.sparta.project03.domain.Article;
import com.sparta.project03.dto.ArticleRequestDto;
import com.sparta.project03.security.UserDetailsImpl;
import com.sparta.project03.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public Article creatArticle(@RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        로그인 되어 있는 사용자의 테이블 ID
        String userName = userDetails.getUser().getUsername();
        Article article = articleService.creatArticle(requestDto, userName);
        return article;
    }

    @GetMapping("/api/articles")
    public List<Article> readArticle() {
        return articleService.readArticles();
    }

    @GetMapping("/api/detail/{id}")
    public Article readDetail(@PathVariable Long id) {
        return articleService.readDetail(id);
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.updateArticle(id, requestDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }
}
