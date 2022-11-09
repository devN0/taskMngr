package com.example.springrestdemo.tasks;

import com.example.springrestdemo.tasks.dtos.CreateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.CreateTaskResponseDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTask(@RequestParam Map<String, Object> reqParams) {
        Object taskName = reqParams.get("taskName");
        CreateTaskRequestDto createTaskRequestDto = new ObjectMapper().convertValue(reqParams, CreateTaskRequestDto.class);
        return "contains following request params : " + taskName + " " + createTaskRequestDto.getDueDate();
    }

    @PostMapping("/add_task")
    public CreateTaskResponseDto createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) {
        CreateTaskResponseDto createTaskResponseDto = taskService.createTask(createTaskRequestDto);
        return createTaskResponseDto;
    }

    @PostMapping("/update_task/{task_id}")
    public UpdateTaskResponseDto updateTask(@PathVariable("task_id") Long id, @RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        UpdateTaskResponseDto updateTaskResponseDto = taskService.updateTask(id, updateTaskRequestDto);
        return updateTaskResponseDto;
    }

    @PostMapping("/add_tasks")
    public List<CreateTaskResponseDto> createTasks(@RequestBody List<CreateTaskRequestDto> createTaskRequestDtoList) {
        List<CreateTaskResponseDto> createTaskResponseDtoList = new ArrayList<>();
        for(CreateTaskRequestDto createTaskRequestDto : createTaskRequestDtoList) {
            createTaskResponseDtoList.add(taskService.createTask(createTaskRequestDto));
        }
        return createTaskResponseDtoList;
    }
}
