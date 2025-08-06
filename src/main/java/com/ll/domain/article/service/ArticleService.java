package com.ll.domain.article.service;

import com.ll.domain.article.entity.Article;
import com.ll.domain.article.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public List<Article> findAll() {
        List<Article> articles = articleRepository.findAll();
        Collections.reverse(articles);
        return articles;
    }

    public Optional<Article> findById(int id) {
        return articleRepository.findById(id);
    }

    public boolean delete(int id) {
        Optional<Article> articleOpt = findById(id);
        if (articleOpt.isPresent()) {
            articleRepository.delete(articleOpt.get());
            return true;
        }
        return false;
    }

    public boolean update(int id, String newTitle, String newContent) {
        Optional<Article> articleOpt = findById(id);
        if (articleOpt.isPresent()) {
            Article article = articleOpt.get();
            article.setTitle(newTitle);
            article.setContent(newContent);
            return true;
        }
        return false;
    }
}