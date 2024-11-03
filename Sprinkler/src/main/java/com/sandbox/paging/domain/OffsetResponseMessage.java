package com.sandbox.paging.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OffsetResponseMessage {
    private int totalPage;
    private List<Article> articles;

    public OffsetResponseMessage(int totalPage, List<Article> articles) {
        this.totalPage = totalPage;
        this.articles = articles;
    }
}
