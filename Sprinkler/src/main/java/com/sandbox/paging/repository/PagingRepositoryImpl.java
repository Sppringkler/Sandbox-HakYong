package com.sandbox.paging.repository;

import com.sandbox.paging.domain.Article;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PagingRepositoryImpl implements PagingRepository{

    private final EntityManager em;

    @Override
    public void saveArticles(List<Article> articles) {
        for (Article article : articles) {
            em.merge(article);
        }
    }

    @Override
    public List<Article> getArticles() {
        return em.createQuery("select a from Article a", Article.class).getResultList();
    }
}
