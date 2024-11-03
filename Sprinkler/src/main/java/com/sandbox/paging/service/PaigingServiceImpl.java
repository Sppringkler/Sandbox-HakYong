package com.sandbox.paging.service;

import com.sandbox.paging.domain.Article;
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
public class PaigingServiceImpl implements PagingService{

    private final PagingRepository pagingRepository;

    @Override
    public void makeArticles(List<Article> articles) {
        pagingRepository.saveArticles(articles);
    }

    @Override
    public OffsetResponseMessage findOffset(int size, int page) {
        // 전체 아티클을 가져온다.
        List<Article> articles = pagingRepository.getArticles();
        int totalSize = articles.size();

        // 전체 페이지 수를 계산
        int totalPage = (int) Math.ceil((double) totalSize / size);

        // 시작 인덱스와 끝 인덱스를 계산
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, totalSize);

        // 요청된 페이지에 해당하는 아티클만 추출
        List<Article> pagedArticles;
        if (startIndex < totalSize) {
            pagedArticles = articles.subList(startIndex, endIndex);
        } else {
            pagedArticles = new ArrayList<>(); // 페이지가 전체 데이터 수를 초과하면 빈 리스트 반환
        }
        return new OffsetResponseMessage(totalPage, pagedArticles);
    }

}
