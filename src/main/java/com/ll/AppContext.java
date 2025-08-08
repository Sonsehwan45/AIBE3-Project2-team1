package com.ll;

import com.ll.domain.article.controller.ArticleController;
import com.ll.domain.article.repository.ArticleRepository;
import com.ll.domain.article.service.ArticleService;
import com.ll.domain.system.controller.SystemController;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppContext {
    public static final Scanner sc;
    public static final DateTimeFormatter formatter;
    public static final ArticleRepository articleRepository;
    public static final ArticleService articleService;
    public static final ArticleController articleController;
    public static final SystemController systemController;

    static {
        sc = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        articleRepository = new ArticleRepository();
        articleService = new ArticleService(articleRepository);
        articleController = new ArticleController(articleService, sc);
        systemController = new SystemController();
        
        // 프로그램 시작 시 자동 로드
        articleService.autoLoad();
    }
    
    public static void close() {
        // 프로그램 종료 시 자동 저장
        articleService.autoSave();
        sc.close();
    }
}
