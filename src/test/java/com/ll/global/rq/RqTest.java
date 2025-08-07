package com.ll.global.rq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RqTest {

    @Test
    @DisplayName("getAction 반환 테스트")
    void t1() {
        Rq rq = new Rq("detail 1");
        assertThat(rq.getAction()).isEqualTo("detail");
    }

    @Test
    @DisplayName("getParam 반환 테스트")
    void t2() {
        Rq rq = new Rq("update 3");
        assertThat(rq.getParam(1)).isEqualTo("3");
    }

    @Test
    @DisplayName("getIntParam 반환 테스트")
    void t3() {
        Rq rq = new Rq("delete abc");
        assertThat(rq.getIntParam(1, -1)).isEqualTo(-1);
    }

    @Test
    @DisplayName("getIntParam_반환 테스트")
    void t4() {
        Rq rq = new Rq("delete 5");
        assertThat(rq.getIntParam(1, -1)).isEqualTo(5);
    }
}