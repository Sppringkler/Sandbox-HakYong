package com.sandbox.todos.repository;

import com.sandbox.todos.domain.Todo;
import java.util.List;

public interface TodoRepository {

    public List<Todo> findAll();

    public Boolean save(Todo todo);

    public Boolean delete(String todo);

    public Boolean update(String id);
}
