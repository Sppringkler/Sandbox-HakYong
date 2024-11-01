package com.sandbox.todos.service;

import com.sandbox.todos.domain.Todo;
import com.sandbox.todos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void delete(String id) {
        todoRepository.delete(id);
    }

    @Override
    public Todo update(Todo todo) {
        return todoRepository.update(todo);
    }

    @Override
    @Transactional(readOnly = true)
    public Todo findById(String id) {
        return todoRepository.findById(id);
    }
}
