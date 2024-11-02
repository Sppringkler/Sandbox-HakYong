package com.sandbox.paging.service;

import com.sandbox.paging.domain.Article;
import com.sandbox.paging.domain.OffsetResponseMessage;

import java.util.List;

public interface PagingService {
    void makeArticles(List<Article> articles);

    OffsetResponseMessage findOffset(int size, int page);
}
