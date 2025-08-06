package com.ll.domain.article.contorller;

import com.ll.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleControllerTest {

    @Test
    @DisplayName("프로그램 시작 시 초기 데이터가 포함된 환영 메시지를 출력한다")
    void t0() {
        String rs = AppTestRunner.run("exit");

        assertThat(rs)
                .startsWith("== 게시판 프로그램 시작 ==")
                .endsWith("프로그램을 종료합니다.\n")
                .contains("명령어) ");
    }

    @Test
    @DisplayName("게시글 작성 시 등록 메시지를 출력한다")
    void t1() {
        String rs = AppTestRunner.run("""
                write
                새로운 제목
                새로운 내용
                exit
                """);

        assertThat(rs)
                .contains("제목: ")
                .contains("내용: ")
                .contains("3번 게시글이 등록되었습니다."); // 초기 데이터 2개 + 새로 1개
    }
}
