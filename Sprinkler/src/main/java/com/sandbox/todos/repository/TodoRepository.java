package com.sandbox.todos.repository;

import com.sandbox.todos.domain.Todo;
import java.util.List;

public interface TodoRepository {

    public List<Todo> findAll();

    public Todo save(Todo todo);

    public void delete(Todo todo);

    public Todo update(Todo todo);

    Todo findById(String id);

}
