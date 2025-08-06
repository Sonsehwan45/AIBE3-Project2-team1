package com.ll.domain.article.repository;

import com.ll.domain.article.entity.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public Article save(Article article) {
        article.setId(++lastId);
        articles.add(article);
        return article;
    }

    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    public Optional<Article> findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst();
    }

    public void delete(Article article) {
        articles.remove(article);
    }
}