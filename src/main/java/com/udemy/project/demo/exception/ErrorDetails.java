package com.udemy.project.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String msg;
    private String path;
    private String errorCode;

}
