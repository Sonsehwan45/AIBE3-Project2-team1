package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> articles = new ArrayList<>();
    private int lastId = 0;

    public int save(String title, String content, String regDate) {
        int id = ++lastId;
        articles.add(new WiseSaying(id, title, content, regDate));
        return id;
    }

    public List<WiseSaying> findAll() {
        return articles;
    }

    public WiseSaying findById(int id) {
        return articles.stream().filter(a -> a.id == id).findFirst().orElse(null);
    }

    public void delete(WiseSaying article) {
        articles.remove(article);
    }
}