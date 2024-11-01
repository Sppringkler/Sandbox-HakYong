package com.sandbox.todos.repository;

import com.sandbox.todos.domain.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {

    private final EntityManager em;

    @Override
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class).getResultList();
    }

    @Override
    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            em.persist(todo);
            return todo;
        } else {
            return em.merge(todo);
        }
    }

    @Override
    public void delete(String id) {
        Todo todo = em.find(Todo.class, id);
        if (todo != null) {
            em.remove(todo);
        }
    }

    @Override
    public Todo update(Todo todo) {
        return em.merge(todo);
    }

    @Override
    public Todo findById(String id) {
        return em.find(Todo.class, id);
    }

}
