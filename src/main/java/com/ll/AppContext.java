package com.ll;

import com.ll.domain.wiseSaying.controller.WiseSayingController;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;
import com.ll.domain.wiseSaying.service.WiseSayingService;
import com.ll.standard.util.TestUtil;

public class AppContext {
    private final WiseSayingController wiseSayingController;

    public AppContext() {
        WiseSayingRepository repo = new WiseSayingRepository();
        TestUtil.generateTestData(repo); // 테스트 데이터 추가
        WiseSayingService service = new WiseSayingService(repo);
        this.wiseSayingController = new WiseSayingController(service);
    }

    public WiseSayingController getWiseSayingController() {
        return wiseSayingController;
    }
}