package com.ll.domain.wiseSaying.controller;


import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService service;
    private final Scanner sc = new Scanner(System.in);

    public WiseSayingController(WiseSayingService service) {
        this.service = service;
    }

    public void write() {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        int id = service.write(title, content);
        System.out.printf("%d번 글이 생성되었습니다.\n", id);
    }

    public void list() {
        List<WiseSaying> articles = service.findAll();
        System.out.println("번호 | 제목 | 작성일");
        System.out.println("-------------------------");
        for (WiseSaying a : articles) {
            System.out.printf("%d / %s / %s \n", a.id, a.title, a.regDate);
        }
    }

    public void detail(int id) {
        WiseSaying article = service.findById(id);
        if (article == null) {
            System.out.println("해당 글이 존재하지 않습니다.");
            return;
        }

        System.out.printf("번호: %d\n제목: %s\n내용: %s\n작성일: %s\n",
                article.id, article.title, article.content, article.regDate);
    }

    public void update(int id) {
        WiseSaying article = service.findById(id);
        if (article == null) {
            System.out.println("해당 글이 존재하지 않습니다.");
            return;
        }

        System.out.print("새 제목: ");
        String title = sc.nextLine();
        System.out.print("새 내용: ");
        String content = sc.nextLine();

        service.update(article, title, content);
        System.out.println("수정되었습니다.");
    }

    public void delete(int id) {
        WiseSaying article = service.findById(id);
        if (article == null) {
            System.out.println("해당 글이 존재하지 않습니다.");
            return;
        }

        service.delete(article);
        System.out.println("게시글이 삭제되었습니다.");
    }
}
