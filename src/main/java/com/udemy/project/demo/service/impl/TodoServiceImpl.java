package com.udemy.project.demo.service.impl;

import com.udemy.project.demo.dto.TodoDTO;
import com.udemy.project.demo.exception.ResourceNotFoundException;
import com.udemy.project.demo.model.TodoItem;
import com.udemy.project.demo.repository.TodoRepository;
import com.udemy.project.demo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {

        TodoItem todoItem = modelMapper.map(todoDTO, TodoItem.class);

        TodoItem savedTodo = todoRepository.save(todoItem);

        TodoDTO savedTodoDTO = modelMapper.map(savedTodo, TodoDTO.class);

        return savedTodoDTO;
    }

    @Override
    public List<TodoDTO> getAllTodo() {

        List<TodoItem> listTodo = todoRepository.findAll();
        //return users.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return listTodo.stream().map(todoItem -> modelMapper.map(todoItem, TodoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDTO getTodo(Long id) {

//        TodoItem todoItem = todoRepository.findById(id).get();
//
//        TodoDTO todoDTO = modelMapper.map(todoItem, TodoDTO.class);

        TodoItem getTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        return modelMapper.map(getTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        TodoItem existedTodo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo Item", "id", id)
        );
        existedTodo.setTitle(todoDTO.getTitle());
        existedTodo.setDescription(todoDTO.getDescription());
        existedTodo.setCompleted(todoDTO.isCompleted());

        TodoItem updateTodo = todoRepository.save(existedTodo);

        return modelMapper.map(updateTodo, TodoDTO.class);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDTO completeTodo(Long id) {

        TodoItem todoItem = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

        todoItem.setCompleted(Boolean.TRUE);

        TodoItem updatedTodo = todoRepository.save(todoItem);

        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO inCompleteTodo(Long id) {

        TodoItem todoItem = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

        todoItem.setCompleted(Boolean.FALSE);

        TodoItem updatedTodo = todoRepository.save(todoItem);

        return modelMapper.map(updatedTodo, TodoDTO.class);
    }
}
