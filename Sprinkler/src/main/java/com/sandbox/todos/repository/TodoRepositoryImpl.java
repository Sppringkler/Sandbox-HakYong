package com.sandbox.todos.repository;

import com.sandbox.todos.domain.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository{

    private final EntityManager em;

    @Override
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class).getResultList();
    }

    @Override
    public Todo save(Todo todo) {
        return null;
    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public Todo update(Todo todo) {
        return null;
    }
}
