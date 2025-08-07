package com.ll.standard.util;

import com.ll.domain.wiseSaying.repository.WiseSayingRepository;

public class TestUtil {
    public static void generateTestData(WiseSayingRepository repo) {
        String now = Util.getCurrentDate();
        repo.save("명언1", "포기하지 마세요", Util.getCurrentDate());
        repo.save("명언2", "성공은 노력에서 온다", Util.getCurrentDate());
        repo.save("명언3", "항상 긍정적으로 생각하세요", Util.getCurrentDate());
    }
}