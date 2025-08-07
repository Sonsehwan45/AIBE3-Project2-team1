package com.ll.domain.cliBoard.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public CliBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CliBoard(Map<String, Object> cliBoardMap) {
        this.id = (int) cliBoardMap.get("id");
        this.title = (String) cliBoardMap.get("title");
        this.content = (String) cliBoardMap.get("content");
        this.createDate = (String) cliBoardMap.get("createDate");
        this.viewCount = (int) cliBoardMap.get("viewCount");
    }

    public boolean isNew() {
        return id == 0;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", id);
        map.put("title", title);
        map.put("content", content);
        map.put("createDate", createDate);
        map.put("viewCount", viewCount);

        return map;
    }
}
