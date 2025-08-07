package com.ll.domain.wiseSaying.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    public int id;
    public String title;
    public String content;
    public String regDate;

    public WiseSaying(int id, String title, String content, String regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
    }

    public WiseSaying(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;

        // 현재 날짜 yyyy-MM-dd 형식으로 저장
        this.regDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
