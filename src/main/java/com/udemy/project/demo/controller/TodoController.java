package com.udemy.project.demo.controller;

import com.udemy.project.demo.dto.TodoDTO;
import com.udemy.project.demo.service.TodoService;
import com.udemy.project.demo.service.impl.TodoServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody @Valid TodoDTO t){

        TodoDTO todoDTO = todoService.addTodo(t);

        return new ResponseEntity<>(todoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TodoDTO>> getAllTodo(){
        List<TodoDTO> list = todoService.getAllTodo();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<TodoDTO> getTodo(@RequestParam(name = "id") Long id){

        TodoDTO todoDTO = todoService.getTodo(id);

        return ResponseEntity.ok(todoDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<TodoDTO> updateTodo(@RequestParam(name = "id") Long id,@RequestBody @Valid TodoDTO t){

        TodoDTO updateTodoDTO = todoService.updateTodo(id, t);

        return ResponseEntity.ok(updateTodoDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTodo(@RequestParam(name = "id") Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long id){
        TodoDTO updatedTodo = todoService.completeTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }

    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable("id") Long id){
        TodoDTO updatedTodo = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }
}
