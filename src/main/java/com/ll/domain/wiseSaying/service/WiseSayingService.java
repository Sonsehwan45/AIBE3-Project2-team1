package com.ll.domain.wiseSaying.service;


import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.repository.WiseSayingRepository;
import com.ll.standard.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository repository;

    public WiseSayingService(WiseSayingRepository repository) {
        this.repository = repository;
    }

    public int write(String title, String content) {
        String regDate = Util.getCurrentDate();
        return repository.save(title, content, regDate);
    }

    public List<WiseSaying> findAll() {
        List<WiseSaying> list = new ArrayList<>(repository.findAll());
        Collections.reverse(list); // 최신순
        return list;
    }

    public WiseSaying findById(int id) {
        return repository.findById(id);
    }

    public void update(WiseSaying article, String title, String content) {
        article.title = title;
        article.content = content;
    }

    public void delete(WiseSaying article) {
        repository.delete(article);
    }
}
