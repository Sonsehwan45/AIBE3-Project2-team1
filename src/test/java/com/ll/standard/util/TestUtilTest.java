package com.ll.standard.util;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestUtilTest {

    @Test
    @DisplayName("generateTestData 호출 시 3개의 더미 데이터가 저장된다")
    void t1() {
        WiseSayingRepository repo = new WiseSayingRepository();

        TestUtil.generateTestData(repo);
        List<WiseSaying> list = repo.findAll();

        assertThat(list).hasSize(3);
        assertThat(list.get(0).title).isEqualTo("명언1");
    }
}