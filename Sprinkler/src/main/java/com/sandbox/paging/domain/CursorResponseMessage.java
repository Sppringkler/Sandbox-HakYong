package com.sandbox.paging.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CursorResponseMessage {
    private String lastId;
    private List<Article> articles;

    public CursorResponseMessage(String lastId, List<Article> articles) {
        this.lastId = lastId;
        this.articles = articles;
    }

}
