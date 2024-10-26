package com.sandbox.todos.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Todo {
    String id;
    String content;
    String completed;
}
