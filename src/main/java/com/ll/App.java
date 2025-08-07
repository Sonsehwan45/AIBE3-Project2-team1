package com.ll;
import java.util.*;

public class App {
    Scanner sc = new Scanner(System.in);
    List<Article> articles = new ArrayList<>();
    int lastId = 0;

    public void run() {
        System.out.println("== 게시판 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            if (rq.getAction().equals("exit")) {
                System.out.println("앱을 종료합니다.");
                break;
            } else if (rq.getAction().equals("write")) {
                writeArticle();
            } else if (rq.getAction().equals("list")) {
                listArticles();
            } else if (rq.getAction().equals("detail")) {
                showDetail(rq.getId());
            } else if (rq.getAction().equals("update")) {
                updateArticle(rq.getId());
            } else if (rq.getAction().equals("delete")) {
                deleteArticle(rq.getId());
            } else {
                System.out.println("알 수 없는 명령입니다.");
            }
        }
    }

    void writeArticle() {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();
        int id = ++lastId;
        String regDate = getCurrentDate();
        articles.add(new Article(id, title, content, regDate));
        System.out.println(id + "번 게시물이 작성되었습니다.");
    }

    void listArticles() {
        System.out.println("번호 / 제목");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article a = articles.get(i);
            System.out.println(a.id + " / " + a.title);
        }
    }

    void showDetail(int id) {
        Article article = findById(id);
        if (article == null) {
            System.out.println("해당 게시물은 존재하지 않습니다.");
            return;
        }
        System.out.println("번호: " + article.id);
        System.out.println("날짜: " + article.regDate);
        System.out.println("제목: " + article.title);
        System.out.println("내용: " + article.content);
    }

    void updateArticle(int id) {
        Article article = findById(id);
        if (article == null) {
            System.out.println("해당 게시물은 존재하지 않습니다.");
            return;
        }
        System.out.print("새 제목: ");
        article.title = sc.nextLine();
        System.out.print("새 내용: ");
        article.content = sc.nextLine();
        System.out.println(id + "번 게시물이 수정되었습니다.");
    }

    void deleteArticle(int id) {
        Article article = findById(id);
        if (article == null) {
            System.out.println("해당 게시물은 존재하지 않습니다.");
            return;
        }
        articles.remove(article);
        System.out.println(id + "번 게시물이 삭제되었습니다.");
    }

    Article findById(int id) {
        for (Article a : articles) {
            if (a.id == id) return a;
        }
        return null;
    }

    String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }
}