package com.sandbox.email.repository;

import com.sandbox.email.entity.EmailVerification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveInfo(EmailVerification email) {
        em.persist(email);
    }

    @Override
    public Optional<EmailVerification> findByEmail(String email) {
        String jpql = "SELECT e FROM EmailVerification e WHERE e.email = :email order by id desc";
        TypedQuery<EmailVerification> query = em.createQuery(jpql, EmailVerification.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteInfoByEmail(String email) {
        String jpql = "DELETE FROM EmailVerification e WHERE e.email = :email";

        Query query = em.createQuery(jpql);
        query.setParameter("email", email);

        query.executeUpdate();
    }

}
