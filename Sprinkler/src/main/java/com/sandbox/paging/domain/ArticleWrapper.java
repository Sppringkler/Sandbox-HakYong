package com.sandbox.paging.domain;

import java.util.List;

public class ArticleWrapper {
    private List<Article> articles;

    // getters and setters
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

