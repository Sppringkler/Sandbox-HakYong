package com.sandbox.paging.service;

import com.sandbox.paging.domain.Article;
import com.sandbox.paging.domain.CursorResponseMessage;
import com.sandbox.paging.domain.OffsetResponseMessage;
import com.sandbox.paging.repository.PagingRepository;
import com.sandbox.paging.repository.PagingRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagingServiceImpl implements PagingService{

    private final PagingRepositoryImpl pagingRepository;

    @Override
    public void makeArticles(List<Article> articles) {
        pagingRepository.saveArticles(articles);
    }

    @Override
    public OffsetResponseMessage findOffset(int size, int page) {
        List<Article> pagedArticles = pagingRepository.findArticle(size,page,true);
        long totalPage = (pagingRepository.countArticle()/size) + 1;

        return new OffsetResponseMessage((int)totalPage, pagedArticles);
    }

    @Override
    public CursorResponseMessage findCursor(int size, int cursorId) {
        List<Article> filteredArticles = pagingRepository.findArticle(size,cursorId,false);
        int lastCursorId = -1;
        if(filteredArticles.size() > 0){
            lastCursorId = Integer.parseInt(filteredArticles.getLast().getId());
        }

        String lastId;
        if(lastCursorId == -1){
            lastId = "undefined";
        }else{
            lastId = String.valueOf(lastCursorId);
        }
        return new CursorResponseMessage(lastId, filteredArticles);
    }




}
