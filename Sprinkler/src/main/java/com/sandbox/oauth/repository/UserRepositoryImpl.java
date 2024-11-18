package com.sandbox.oauth.repository;

import com.sandbox.oauth.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public Optional<User> findByRefreshToken(String refreshToken) {
        String jpql = "SELECT u FROM User u WHERE u.refreshToken = :refreshToken";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("refreshToken", refreshToken);

        try {
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            // 결과가 없거나 예외가 발생할 경우 Optional.empty() 반환
            return Optional.empty();
        }
    }

    public void delete(User user) {
        if (user != null) {
            em.remove(user);
        }
    }

}
