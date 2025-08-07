package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    private WiseSayingFileRepository repository;

    @BeforeEach
    void setUp() {
        repository = new WiseSayingFileRepository();
        repository.clear(); // 테스트 전 초기화 (파일 삭제 등)
    }

    @Test
    @DisplayName("저장 후 불러오기 테스트")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying(1, "제목", "내용", "작성일");
        repository.save(wiseSaying);

        List<WiseSaying> list = repository.findAll();

        assertThat(list).hasSize(1);
        assertThat(list.get(0).title).isEqualTo("제목");
        assertThat(list.get(0).regDate).isEqualTo("작성일");
    }

    @Test
    @DisplayName("저장 없이 불러오기 시 빈 리스트 반환 테스트")
    void t2() {
        List<WiseSaying> list = repository.findAll();
        assertThat(list).isEmpty();
    }
}