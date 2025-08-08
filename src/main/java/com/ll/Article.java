package com.ll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 게시글 데이터 클래스
class Article {
    private int id;
    private String title;
    private String content;
    private LocalDateTime regDate; // 등록일
    // 해당 날짜 형식은 바뀔 이유가 없고, Article 마다 같은 기능을 하기 떄문에 날짜 객체를 하나만 생성해서 함께 공유하는 것이 효율적임 ==> static final
    private static final DateTimeFormatter forPrintDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private int count = 0; // detail일 때의 조회수

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getForPrintRegDate() {
        return regDate.format(forPrintDateTimeFormatter);
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
    }
}
