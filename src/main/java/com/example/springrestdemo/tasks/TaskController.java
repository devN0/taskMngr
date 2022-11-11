package com.example.springrestdemo.tasks;

import com.example.springrestdemo.commons.ErrorResponseDto;
import com.example.springrestdemo.tasks.dtos.*;
import com.example.springrestdemo.tasks.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id) {
        TaskResponseDto taskResponseDto = taskService.getTaskById(id);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/tasks/"+taskResponseDto.getId()))
                .body(taskResponseDto);
    }

    @PostMapping("/add_task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) throws IllegalArgumentException {
        TaskResponseDto taskResponseDto = taskService.createTask(createTaskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PostMapping("/update_task/{task_id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable("task_id") Long id, @RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        TaskResponseDto taskResponseDto = taskService.updateTask(id, updateTaskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PostMapping("/add_tasks")
    public ResponseEntity<List<TaskResponseDto>> createTasks(@RequestBody List<CreateTaskRequestDto> createTaskRequestDtoList) {
        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        for(CreateTaskRequestDto createTaskRequestDto : createTaskRequestDtoList) {
            taskResponseDtoList.add(taskService.createTask(createTaskRequestDto));
        }
        return ResponseEntity.ok(taskResponseDtoList);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            TaskNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleExceptions(Exception e) {
        if(e instanceof TaskNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getMessage()));
        }
        return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage()));
    }
}
