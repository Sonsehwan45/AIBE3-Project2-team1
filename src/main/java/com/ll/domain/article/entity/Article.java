package com.ll.domain.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;
    private String regDate;
}
