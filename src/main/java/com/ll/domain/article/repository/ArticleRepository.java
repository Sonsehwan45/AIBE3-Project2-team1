package com.ll.domain.article.repository;

import com.ll.domain.article.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public Article save(Article article) {
        article.setId(++lastId);
        articles.add(article);
        return article;
    }
}