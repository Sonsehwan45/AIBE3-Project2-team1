package com.ll.domain.article.controller;

import com.ll.AppContext;
import com.ll.Rq;
import com.ll.domain.article.entity.Article;
import com.ll.domain.article.service.ArticleService;

import java.util.List;
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

    public void list() {
        List<Article> articles = articleService.findAll();

        if (articles.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        System.out.println("번호 | 제목 | 등록일");
        System.out.println("--------------------------");
        for (Article article : articles) {
            System.out.printf("%d | %s | %s\n",
                    article.getId(), article.getTitle(), article.getRegDate().substring(0, 10));
        }
    }

    public void delete(Rq rq) {
        // getParam() 대신 getIntParam()을 사용하도록 수정
        int id = rq.getIntParam("id", 0);
        if (id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
            return;
        }

        if (articleService.delete(id)) {
            System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
        } else {
            System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
        }
    }
}