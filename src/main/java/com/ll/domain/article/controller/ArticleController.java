package com.ll.domain.article.controller;

import com.ll.Rq;
import com.ll.domain.article.entity.Article;
import com.ll.domain.article.service.ArticleService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ArticleController {
    private final ArticleService articleService;
    private final Scanner scanner;

    public ArticleController(ArticleService articleService, Scanner scanner) {
        this.articleService = articleService;
        this.scanner = scanner;
    }

    public void actionWrite() {
        System.out.print("제목: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("내용: ");
        String content = scanner.nextLine().trim();
        
        Article article = articleService.write(title, content);
        System.out.println(article.getId() + "번 게시글이 작성되었습니다.");
    }

    public void actionList() {
        List<Article> articles = articleService.findAllSorted("default");
        displayArticleList(articles, "기본 정렬 (최신순)");
    }

    public void actionList(String sortType) {
        List<Article> articles = articleService.findAllSorted(sortType);
        String sortDescription = getSortDescription(sortType);
        displayArticleList(articles, sortDescription);
    }

    public void actionList(Rq rq) {
        String sortType = rq.getParam("sort", "default");
        actionList(sortType);
    }

    private void displayArticleList(List<Article> articles, String sortDescription) {
        System.out.println("게시글 목록 (" + sortDescription + ")");
        System.out.println("번호 | 제목       | 등록일     | 조회수");
        System.out.println("---------------------------------------");
        
        for (Article article : articles) {
            System.out.printf("%-4d | %-10s | %-10s | %d%n", 
                article.getId(), 
                article.getTitle(), 
                article.getRegDate(),
                article.getViewCount()
            );
        }
    }

    private String getSortDescription(String sortType) {
        switch (sortType.toLowerCase()) {
            case "id": return "번호 오름차순";
            case "id-desc": return "번호 내림차순";
            case "date": return "날짜 오름차순";
            case "date-desc": return "날짜 내림차순";
            case "view": return "조회수 높은순";
            case "view-asc": return "조회수 낮은순";
            case "title": return "제목 가나다순";
            case "title-desc": return "제목 역순";
            default: return "기본 정렬 (최신순)";
        }
    }

    public void actionDetail(int id) {
        Optional<Article> articleOptional = articleService.findByIdAndIncreaseViewCount(id);
        
        if (articleOptional.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }
        
        Article article = articleOptional.get();
        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
        System.out.println("조회수: " + article.getViewCount());
    }

    public void actionUpdate(int id) {
        Optional<Article> articleOptional = articleService.findById(id);
        
        if (articleOptional.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }
        
        Article article = articleOptional.get();
        System.out.println("기존 제목: " + article.getTitle());
        System.out.print("새 제목: ");
        String title = scanner.nextLine().trim();
        
        System.out.println("기존 내용: " + article.getContent());
        System.out.print("새 내용: ");
        String content = scanner.nextLine().trim();
        
        articleService.update(id, title, content);
        System.out.println(id + "번 게시글이 수정되었습니다.");
    }

    public void actionDelete(int id) {
        if (articleService.deleteById(id)) {
            System.out.println(id + "번 게시글이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
        }
    }

    // Rq 객체를 사용한 메서드들
    public void actionDetail(Rq rq) {
        if (!rq.hasParam("id")) {
            System.out.println("번호를 입력해주세요.");
            return;
        }
        
        if (!rq.hasValidId()) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }
        
        int id = rq.getParamAsInt("id", -1);
        actionDetail(id);
    }

    public void actionUpdate(Rq rq) {
        if (!rq.hasParam("id")) {
            System.out.println("올바른 명령어 형식: update [번호]");
            return;
        }
        
        if (!rq.hasValidId()) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }
        
        int id = rq.getParamAsInt("id", -1);
        actionUpdate(id);
    }

    public void actionDelete(Rq rq) {
        if (!rq.hasParam("id")) {
            System.out.println("올바른 명령어 형식: delete [번호]");
            return;
        }
        
        if (!rq.hasValidId()) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }
        
        int id = rq.getParamAsInt("id", -1);
        actionDelete(id);
    }

    public void actionSearch(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("검색어를 입력해주세요.");
            return;
        }

        List<Article> searchResults = articleService.search(keyword);
        
        if (searchResults.isEmpty()) {
            System.out.println("\"" + keyword + "\"에 대한 검색 결과가 없습니다.");
            return;
        }

        System.out.println("\"" + keyword + "\" 검색 결과: " + searchResults.size() + "개");
        System.out.println("번호 | 제목       | 등록일     | 조회수");
        System.out.println("---------------------------------------");
        
        for (int i = searchResults.size() - 1; i >= 0; i--) {
            Article article = searchResults.get(i);
            System.out.printf("%-4d | %-10s | %-10s | %d%n", 
                article.getId(), 
                article.getTitle(), 
                article.getRegDate(),
                article.getViewCount()
            );
        }
    }

    public void actionSearch(Rq rq) {
        String keyword = rq.getParam("keyword", "");
        actionSearch(keyword);
    }

    public void actionSave() {
        try {
            articleService.saveToFile("articles.txt");
            System.out.println("게시글 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("저장 실패: " + e.getMessage());
        }
    }

    public void actionLoad() {
        try {
            articleService.loadFromFile("articles.txt");
            System.out.println("게시글 데이터를 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("불러오기 실패: " + e.getMessage());
        }
    }

    public void showSortHelp() {
        System.out.println("=== 정렬 옵션 도움말 ===");
        System.out.println("list          # 기본 정렬 (최신순)");
        System.out.println("list --id     # 번호 오름차순");
        System.out.println("list --id-desc # 번호 내림차순");
        System.out.println("list --date   # 날짜 오름차순");
        System.out.println("list --date-desc # 날짜 내림차순");
        System.out.println("list --view   # 조회수 높은순");
        System.out.println("list --view-asc # 조회수 낮은순");
        System.out.println("list --title  # 제목 가나다순");
        System.out.println("list --title-desc # 제목 역순");
    }
}