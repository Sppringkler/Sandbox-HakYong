package com.sandbox.paging.repository;

import com.sandbox.paging.domain.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
            if (article.getId() == null) {
                em.persist(article);
            } else {
                em.merge(article);
            }
        }
    }

    @Override
    public List<Article> getArticles() {
        return em.createQuery("select a from Article a", Article.class).getResultList();
    }

    public List<Article> findArticle(int size, int flagNum, boolean flag){
        int offset = 0;
        if(flag){ // Paging 처리하기
            offset = size * (flagNum-1);
        }else{ // Cursor 처리하기
            offset = flagNum;
        }

        TypedQuery<Article> query = em.createQuery("SELECT a FROM Article a", Article.class);
        query.setFirstResult(offset); // offset
        query.setMaxResults(size); // limit

        return query.getResultList();
    }

    public Long countArticle(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(a) FROM Article a", Long.class);
        return query.getSingleResult();
    }
}
