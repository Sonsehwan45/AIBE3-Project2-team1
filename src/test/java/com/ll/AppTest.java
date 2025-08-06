package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    @Test
    @DisplayName("프로그램 시작 시 초기 데이터가 포함된 환영 메시지를 출력한다")
    void t0() {
        String rs = AppTestRunner.run("exit"); // 'exit'만 입력하여 바로 종료

        assertThat(rs)
                .startsWith("== 게시판 프로그램 시작 ==")
                .endsWith("프로그램을 종료합니다.\n")
                .contains("명령어) ");
    }

    @Test
    @DisplayName("게시글 작성 시 등록 메시지를 출력한다")
    void t1() {
        String rs = AppTestRunner.run("""
                등록
                새로운 제목
                새로운 내용
                제목
                """);

        assertThat(rs)
                .contains("제목: ")
                .contains("내용: ")
                .contains("1번 게시글이 등록되었습니다."); // 초기 데이터 2개 + 새로 1개
    }

    @Test
    @DisplayName("게시글을 연달아 작성하면 ID가 증가한다")
    void t2() {
        String rs = AppTestRunner.run("""
                등록
                제목1
                내용1
                등록
                제목2
                내용2
                종료
                """);

        assertThat(rs)
                .contains("1번 게시글이 등록되었습니다.")
                .contains("2번 게시글이 등록되었습니다.");
    }

    @Test
    @DisplayName("게시글 목록을 조회하면 최신순으로 출력된다")
    void t3() {
        String rs = AppTestRunner.run("""
                등록
                제목1
                내용1
                등록
                제목2
                내용2
                목록
                종료
                """);

        assertThat(rs)
                .contains("번호 | 제목 | 등록일")
                .contains("--------------------------")
                .contains("2 | 제목2 |")
                .contains("1 | 제목1 |")
                // indexOf를 사용하여 출력 순서 검증 (2번 글이 1번 글보다 먼저 나와야 함)
                .satisfies(s -> {
                    int pos2 = s.indexOf("2 | 제목2");
                    int pos1 = s.indexOf("1 | 제목1");
                    assertThat(pos2).isLessThan(pos1);
                });
    }
}
