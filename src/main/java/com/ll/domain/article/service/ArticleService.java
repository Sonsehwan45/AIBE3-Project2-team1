package com.ll.domain.article.service;

import com.ll.domain.article.entity.Article;
import com.ll.domain.article.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public int write(String title, String content) {
        String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Article article = new Article(0, title, content, regDate);
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }
}