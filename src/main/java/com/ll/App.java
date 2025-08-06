package com.ll;

import com.ll.domain.article.controller.ArticleController;
import com.ll.domain.article.repository.ArticleRepository;
import com.ll.domain.article.service.ArticleService;


public class App {
    public void run() {
        System.out.println("== 게시판 프로그램 시작 ==");

        AppContext.init();

        ArticleRepository articleRepository = new ArticleRepository();
        ArticleService articleService = new ArticleService(articleRepository);
        ArticleController articleController = new ArticleController(articleService);

        while (true) {
            System.out.print("명령어) ");
            String command = AppContext.getScanner().nextLine().trim();
            Rq rq = new Rq(command);

            switch (rq.getAction()) {
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    AppContext.close();
                    return;
                case "write":
                    articleController.write();
                    break;
                default:
                    System.out.println("알 수 없는 명령어입니다.");
                    break;
            }
        }
    }
}