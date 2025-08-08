package com.ll.domain.article.controller;

import com.ll.domain.article.repository.ArticleRepository;
import com.ll.domain.article.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleControllerTest {
    private ArticleController articleController;
    private ArticleService articleService;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        ArticleRepository articleRepository = new ArticleRepository();
        articleService = new ArticleService(articleRepository);
    }

    @Test
    @DisplayName("게시글 작성 테스트")
    void testActionWrite() {
        String input = "테스트 제목\n테스트 내용\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        articleController = new ArticleController(articleService, scanner);
        
        articleController.actionWrite();
        
        String output = outputStream.toString();
        assertThat(output).contains("제목:");
        assertThat(output).contains("내용:");
        assertThat(output).contains("1번 게시글이 작성되었습니다.");
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void testActionList() {
        Scanner scanner = new Scanner("");
        articleController = new ArticleController(articleService, scanner);
        
        articleService.write("첫번째 글", "첫번째 내용");
        articleService.write("두번째 글", "두번째 내용");
        
        articleController.actionList();
        
        String output = outputStream.toString();
        assertThat(output).contains("번호 | 제목       | 등록일");
        assertThat(output).contains("첫번째 글");
        assertThat(output).contains("두번째 글");
    }

    @Test
    @DisplayName("게시글 상세보기 테스트")
    void testActionDetail() {
        Scanner scanner = new Scanner("");
        articleController = new ArticleController(articleService, scanner);
        
        articleService.write("테스트 제목", "테스트 내용입니다.");
        
        articleController.actionDetail(1);
        
        String output = outputStream.toString();
        assertThat(output).contains("번호: 1");
        assertThat(output).contains("제목: 테스트 제목");
        assertThat(output).contains("내용: 테스트 내용입니다.");
    }

    @Test
    @DisplayName("존재하지 않는 게시글 상세보기 테스트")
    void testActionDetailNotFound() {
        Scanner scanner = new Scanner("");
        articleController = new ArticleController(articleService, scanner);
        
        articleController.actionDetail(999);
        
        String output = outputStream.toString();
        assertThat(output).contains("999번 게시글은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void testActionUpdate() {
        String input = "수정된 제목\n수정된 내용\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        articleController = new ArticleController(articleService, scanner);
        
        articleService.write("원래 제목", "원래 내용");
        
        articleController.actionUpdate(1);
        
        String output = outputStream.toString();
        assertThat(output).contains("기존 제목: 원래 제목");
        assertThat(output).contains("기존 내용: 원래 내용");
        assertThat(output).contains("1번 게시글이 수정되었습니다.");
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void testActionDelete() {
        Scanner scanner = new Scanner("");
        articleController = new ArticleController(articleService, scanner);
        
        articleService.write("삭제할 글", "삭제할 내용");
        
        articleController.actionDelete(1);
        
        String output = outputStream.toString();
        assertThat(output).contains("1번 게시글이 삭제되었습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 게시글 삭제 테스트")
    void testActionDeleteNotFound() {
        Scanner scanner = new Scanner("");
        articleController = new ArticleController(articleService, scanner);
        
        articleController.actionDelete(999);
        
        String output = outputStream.toString();
        assertThat(output).contains("999번 게시글은 존재하지 않습니다.");
    }
}