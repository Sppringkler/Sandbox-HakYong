package com.sandbox.paging.repository;

import com.sandbox.paging.domain.Article;

import java.util.List;

public interface PagingRepository {
    void saveArticles(List<Article> articles);

    List<Article> getArticles();
}
