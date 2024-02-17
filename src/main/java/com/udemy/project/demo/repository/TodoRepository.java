package com.udemy.project.demo.repository;

import com.udemy.project.demo.model.TodoItem;
import com.udemy.project.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {


}