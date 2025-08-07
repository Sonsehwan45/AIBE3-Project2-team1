package com.ll.domain.cliBoard.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CliBoard {
    private int id;
    private String title;
    private String content;
    private String createDate;
    private int viewCount;
    private LocalDateTime modifyDate;

    public CliBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public boolean isNew() {
        return id == 0;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}
