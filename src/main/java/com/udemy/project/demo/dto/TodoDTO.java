package com.udemy.project.demo.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoDTO {

    private Long id;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Description must not be empty")
    private String description;

    private boolean isCompleted;
}
