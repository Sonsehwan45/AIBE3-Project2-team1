package com.ll;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    void 앱이_exit_명령어를_입력하면_종료된다() {
        // given
        String input = "exit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        App app = new App();

        // when & then (정상 종료되면 테스트 성공)
        app.run();
        assertThat(true).isTrue(); // 예외 없이 종료되었음을 확인
    }
}
