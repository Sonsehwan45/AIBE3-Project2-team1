package com.ll.system.controller;

import com.ll.domain.system.controller.SystemController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SystemControllerTest {

    @Test
    @DisplayName("exit 명령어를 입력하면 종료")
    void t1() {
        // given
        SystemController controller = new SystemController();

        // when
        boolean result = controller.shouldExit("exit");

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("exit가 아닌 경우 종료하지 않음")
    void t2() {
        SystemController controller = new SystemController();
        assertThat(controller.shouldExit("write")).isFalse();
    }
}