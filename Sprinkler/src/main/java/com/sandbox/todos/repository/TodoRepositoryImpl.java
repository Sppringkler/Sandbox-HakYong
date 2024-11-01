package com.sandbox.todos.repository;

import com.sandbox.todos.domain.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TodoRepositoryImpl implements TodoRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Todo> findAll() {
        TypedQuery<Todo> query = em.createQuery("select t from Todo t", Todo.class);
        List<Todo> todoList = query.getResultList();

        return todoList;
    }

    @Override
    public Boolean save(Todo todo) {
        em.persist(todo);
        em.flush();

        Todo checkTodo = em.find(Todo.class,todo.getId());
        if(checkTodo != null) {
            return true;
        }else{
            return  false;
        }

    }

    @Override
    public Boolean delete(String id) {
        Todo findTodo = em.find(Todo.class, Long.parseLong(id));
        em.remove(findTodo);
        em.flush();

        Todo checkTodo = em.find(Todo.class,id);
        if(checkTodo == null){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Boolean update(String id) {
        Todo todo = em.find(Todo.class, Long.parseLong(id));
        int preCompleted = todo.getCompleted();
        if(todo != null){
            if(todo.getCompleted()==1) {
                todo.setCompleted(0);
            }else{
                todo.setCompleted(1);
            }
        }
        em.flush();

        Todo checkTodo =em.find(Todo.class, Long.parseLong(id));
        if(checkTodo != null && checkTodo.getCompleted() != preCompleted){
            return true;
        }else{
            return false;
        }

    }
}
