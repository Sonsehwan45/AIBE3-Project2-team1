package com.ll.domain.cliBoard.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class cliBoard {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public cliBoard (String title, String content) {
        this.title = title;
        this.content = content;
    }
    public boolean isNew() {
        return id == 0;
    }

}
