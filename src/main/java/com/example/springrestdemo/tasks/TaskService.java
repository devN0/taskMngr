package com.example.springrestdemo.tasks;

import com.example.springrestdemo.tasks.dtos.*;
import com.example.springrestdemo.tasks.exceptions.TaskNotFoundException;
import com.example.springrestdemo.tasks.mappers.TaskMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = TaskMapper.INSTANCE;
        this.modelMapper = modelMapper;
    }

    public TaskResponseDto getTaskById(Long id) throws TaskNotFoundException {
//        TaskEntity taskEntity = taskRepository.findTaskEntityById(id);
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
        TaskResponseDto taskResponseDto = modelMapper.map(taskEntity, TaskResponseDto.class);
        return taskResponseDto;
    }

    public TaskResponseDto createTask(CreateTaskRequestDto createTaskRequestDto) throws IllegalArgumentException {
        if(createTaskRequestDto.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date can't be in past");
        }

        TaskEntity taskEntity1 = modelMapper.map(createTaskRequestDto, TaskEntity.class);
//        taskEntity1.setCompleted(false);
        TaskEntity savedTaskEntity1 = taskRepository.save(taskEntity1);

        TaskResponseDto taskResponseDto1 = modelMapper.map(savedTaskEntity1, TaskResponseDto.class);

//        TaskMapper taskMapper = TaskMapper.INSTANCE;
//        TaskEntity taskEntity = taskMapper.mapCreateTaskRequestDtoToTaskEntity(createTaskRequestDto);
//        taskEntity.setCompleted(false);
//
//        TaskEntity savedTaskEntity = taskRepository.save(taskEntity);
//
//        TaskResponseDto taskResponseDto = taskMapper.mapTaskEntityToTaskResponseDto(savedTaskEntity);

        return taskResponseDto1;
    }

    public TaskResponseDto updateTask(Long id, UpdateTaskRequestDto updateTaskRequestDto) {
        TaskEntity savedTaskEntity = taskRepository.findTaskEntityById(id);

        TaskEntity updatedTaskEntity = taskMapper.mapUpdateTaskRequestDtoToTaskEntity(updateTaskRequestDto, savedTaskEntity);

        TaskEntity savedUpdatedTaskEntity = taskRepository.save(updatedTaskEntity);

        TaskResponseDto taskResponseDto = taskMapper.mapTaskEntityToTaskResponseDto(savedUpdatedTaskEntity);

        return taskResponseDto;
    }
}
