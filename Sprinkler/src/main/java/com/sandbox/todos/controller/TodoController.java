package com.sandbox.todos.controller;

import com.sandbox.todos.domain.Todo;
import com.sandbox.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("todos")
    public Map<String, List<Todo>> findAll() throws Exception {
        Map<String, List<Todo>> response = new HashMap<>();
        response.put("todos", todoService.findAll());

        return response;
    }


    @PostMapping("todos")
    public ResponseEntity<String> insert(@RequestBody Map<String, String> payload){
        boolean isSuccess = todoService.save(payload.get("content"));

        if (isSuccess) {
            return new ResponseEntity<>("insert success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("insert failed ", HttpStatus.NOT_FOUND);
        }

    }


    @PatchMapping("todos/{todoId}")
    public ResponseEntity<String> update(@PathVariable("todoId") String id){
        boolean isSuccess = todoService.update(id);

        if (isSuccess) {
            return new ResponseEntity<>("update success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("update failed ", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("todos/{todos}")
    public ResponseEntity<String> delete(@PathVariable("todos") String todos){
        boolean isSuccess = todoService.delete(todos);

        if (isSuccess) {
            return new ResponseEntity<>("delete success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("delete failed ", HttpStatus.NOT_FOUND);
        }

    }
}
