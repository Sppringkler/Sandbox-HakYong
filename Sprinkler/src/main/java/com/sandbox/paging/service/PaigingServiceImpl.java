package com.sandbox.paging.service;

import com.sandbox.paging.domain.Article;
import com.sandbox.paging.domain.CursorResponseMessage;
import com.sandbox.paging.domain.OffsetResponseMessage;
import com.sandbox.paging.repository.PagingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PaigingServiceImpl implements PagingService{

    private final PagingRepository pagingRepository;

    @Override
    public void makeArticles(List<Article> articles) {
        pagingRepository.saveArticles(articles);
    }

    @Override
    public OffsetResponseMessage findOffset(int size, int page) {
        if (size <= 0 || page <= 0) { // page가 1 이상이어야 한다고 가정
            throw new IllegalArgumentException("Page size and page number must be positive.");
        }

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
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be positive.");
        }

        // 전체 데이터를 가져온다
        List<Article> articles = pagingRepository.getArticles();

        // 커서 이후 데이터를 담을 리스트 생성
        List<Article> filteredArticles = new ArrayList<>();

        // 전체 articles 리스트를 순회하면서 cursorId 이후의 데이터만 추가
        for (Article article : articles) {
            if (Integer.parseInt(article.getId()) > cursorId) { // cursorId 이후의 데이터
                filteredArticles.add(article);
                if (filteredArticles.size() == size) { // size 개수만큼 수집하면 중단
                    break;
                }
            }
        }

        // 다음 cursorId 설정
        int nextCursorId = filteredArticles.isEmpty() ? -1 : Integer.parseInt(filteredArticles.get(filteredArticles.size() - 1).getId());

        return new CursorResponseMessage(Integer.toString(nextCursorId), filteredArticles);
    }




}
