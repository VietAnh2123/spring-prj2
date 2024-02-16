package com.udemy.project.demo.repository;

import com.udemy.project.demo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}