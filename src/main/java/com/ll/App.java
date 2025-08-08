package com.ll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 프로그램 실행 로직
public class App {
    void run() {
        int lastId = 0;
        LocalDateTime now;
        Scanner scanner = new Scanner(System.in);
        List<Article> articleList = new ArrayList<>();

        while (true) {
            System.out.print("명령어: ");
            String cmd = scanner.nextLine(); // 커맨드 입력

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "write" -> {
                    Article article = new Article();

                    System.out.print("제목: ");
                    String title = scanner.nextLine().trim();
                    article.setTitle(title);

                    System.out.print("내용: ");
                    String content = scanner.nextLine().trim();
                    article.setContent(content);

                    lastId++;
                    article.setId(lastId);

                    now = LocalDateTime.now();
                    article.setRegDate(now);

                    articleList.add(article); // 리스트에 article 객체 추가
                    System.out.println("=> 게시글이 등록되었습니다.");
                    // 나중에 게시글이 정상적으로 등록안될 때 예외처리하기
                }
                case "list" -> {
                    System.out.println("번호 | 제목 | 등록일");
                    System.out.println("-----------------------------");
                    Article article;
                    // 반복문으로 리스트로부터 하나씩 article 객체를 꺼내서 필드 출력
                    for (int i = 0; i < articleList.size(); i++) {
                        article = articleList.get(i);
                        System.out.println("%d | %s | %s".formatted(article.getId(), article.getTitle(), article.getForPrintRegDate()));
                    }
                }
                case "detail" -> {
                    Article article = null;
                    for (Article arti: articleList) {
                        if(arti.getId()== rq.getId()) article = arti;
                    }
                    article.increaseCount();
                    System.out.println("번호: %d".formatted(article.getId()));
                    System.out.println("제목: %s".formatted(article.getTitle()));
                    System.out.println("내용: %s".formatted(article.getContent()));
                    System.out.println("등록일: %s".formatted(article.getForPrintRegDate()));
                    System.out.println("조회수: %d".formatted(article.getCount()));
                }
                case "update" -> {
                    Article article = null;
                    for (Article arti: articleList) {
                        if(arti.getId()== rq.getId()) article = arti;
                    }

                    System.out.print("제목 (현재: %s): ".formatted(article.getTitle()));
                    String title = scanner.nextLine().trim();
                    article.setTitle(title);

                    System.out.print("내용 (현재: %s): ".formatted(article.getContent()));
                    String content = scanner.nextLine().trim();
                    article.setContent(content);

                    System.out.println("=> 게시글이 수정되었습니다.");
                }
                case "delete" -> {
                    Article article = null;
                    for (Article arti: articleList) {
                        if(arti.getId()== rq.getId()) article = arti;
                    }
                    articleList.remove(article);
                    System.out.println("=> 게시글이 삭제되었습니다.");
                }
                case "exit" -> {
                    System.out.println("=> 프로그램을 종료합니다.");
                    return;
                }
            }
        }
    }
}
