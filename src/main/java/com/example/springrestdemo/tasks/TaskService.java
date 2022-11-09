package com.example.springrestdemo.tasks;

import com.example.springrestdemo.tasks.dtos.CreateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.CreateTaskResponseDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskResponseDto;
import com.example.springrestdemo.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public CreateTaskResponseDto createTask(CreateTaskRequestDto createTaskRequestDto) {
        TaskMapper taskMapper = TaskMapper.INSTANCE;
        TaskEntity taskEntity = taskMapper.mapCreateTaskRequestDtoToTaskEntity(createTaskRequestDto);
        taskEntity.setCompleted(false);

        TaskEntity savedTaskEntity = taskRepository.save(taskEntity);

        CreateTaskResponseDto createTaskResponseDto = taskMapper.mapTaskEntityToCreateTaskResponseDto(savedTaskEntity);
        return createTaskResponseDto;
    }

    public UpdateTaskResponseDto updateTask(Long id, UpdateTaskRequestDto updateTaskRequestDto) {
        TaskEntity savedTaskEntity = taskRepository.findTaskEntityById(id);

        TaskMapper taskMapper = TaskMapper.INSTANCE;
        TaskEntity updatedTaskEntity = taskMapper.mapUpdateTaskRequestDtoToTaskEntity(updateTaskRequestDto, savedTaskEntity);

        TaskEntity savedUpdatedTaskEntity = taskRepository.save(updatedTaskEntity);

        UpdateTaskResponseDto updateTaskResponseDto = taskMapper.mapTaskEntityToUpdateTaskResponseDto(savedUpdatedTaskEntity);

        return updateTaskResponseDto;
    }
}
