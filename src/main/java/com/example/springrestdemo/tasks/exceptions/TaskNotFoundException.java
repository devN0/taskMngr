package com.example.springrestdemo.tasks.exceptions;

public class TaskNotFoundException extends IllegalArgumentException {
    public TaskNotFoundException(Long id) {
        super("No task found with id: " + id);
    }
}
