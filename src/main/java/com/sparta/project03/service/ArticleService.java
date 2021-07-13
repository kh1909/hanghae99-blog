package com.sparta.project03.service;


import com.sparta.project03.domain.Article;
import com.sparta.project03.dto.ArticleRequestDto;
import com.sparta.project03.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Article creatArticle(ArticleRequestDto requestDto, Long userId, String userName) {
        Article article = new Article(requestDto, userId, userName);
        articleRepository.save(article);
        return article;
    }

    @Transactional
    public List<Article> readArticles() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Article readDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        articleRepository.findById(id);
        return article;
    }

    @Transactional
    public Long updateArticle(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        article.update(requestDto);
        return article.getId();
    }

    @Transactional
    public Long deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        articleRepository.deleteById(id);
        return article.getId();
    }
}