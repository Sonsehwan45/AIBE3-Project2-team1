package com.ll.domain.article.controller;

import com.ll.domain.article.service.ArticleService;
import com.ll.AppContext;

import java.util.Scanner;

public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    private Scanner getScanner() {
        return AppContext.getScanner();
    }

    public void write() {
        System.out.print("제목: ");
        String title = getScanner().nextLine();
        System.out.print("내용: ");
        String content = getScanner().nextLine();
        int id = articleService.write(title, content);
        System.out.printf("%d번 게시글이 등록되었습니다.\n", id);
    }
}