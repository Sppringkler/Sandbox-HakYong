package com.sandbox.paging.service;

import com.sandbox.paging.domain.Article;
import com.sandbox.paging.domain.CursorResponseMessage;
import com.sandbox.paging.domain.OffsetResponseMessage;
import com.sandbox.paging.repository.PagingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagingServiceImpl implements PagingService{

    private final PagingRepository pagingRepository;

    @Override
    public void makeArticles(List<Article> articles) {
        pagingRepository.saveArticles(articles);
    }

    @Override
    public OffsetResponseMessage findOffset(int size, int page) {
        List<Article> articles = pagingRepository.getArticles();
        int totalSize = articles.size();
        int totalPage = (int) Math.ceil((double) totalSize / size);

        if (page > totalPage) {
            return new OffsetResponseMessage(totalPage, new ArrayList<>());
        }

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalSize);

        List<Article> pagedArticles = articles.subList(startIndex, endIndex);

        return new OffsetResponseMessage(totalPage, pagedArticles);
    }

    @Override
    public CursorResponseMessage findCursor(int size, int cursorId) {
        List<Article> articles = pagingRepository.getArticles();

        List<Article> filteredArticles = new ArrayList<>();

        for (Article article : articles) {
            if (Integer.parseInt(article.getId()) > cursorId) {
                filteredArticles.add(article);
                if (filteredArticles.size() == size) {
                    break;
                }
            }
        }

        int nextCursorId = filteredArticles.isEmpty() ? -1 : Integer.parseInt(filteredArticles.get(filteredArticles.size() - 1).getId());

        return new CursorResponseMessage(Integer.toString(nextCursorId), filteredArticles);
    }




}
