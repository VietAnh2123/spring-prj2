package com.udemy.project.demo.service;

import com.udemy.project.demo.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    TodoDTO addTodo(TodoDTO t);

    List<TodoDTO> getAllTodo();

    TodoDTO getTodo(Long id);

    TodoDTO updateTodo(Long id, TodoDTO t);

    void deleteTodo(Long id);

    TodoDTO completeTodo(Long id);

    TodoDTO inCompleteTodo(Long id);
}
