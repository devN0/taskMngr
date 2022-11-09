package com.example.springrestdemo.tasks;

import com.example.springrestdemo.tasks.dtos.CreateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.CreateTaskResponseDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskResponseDto;
import com.example.springrestdemo.tasks.mappers.TaskMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public CreateTaskResponseDto createTask(CreateTaskRequestDto createTaskRequestDto) {
        ModelMapper modelMapper = new ModelMapper();

        TaskEntity taskEntity1 = modelMapper.map(createTaskRequestDto, TaskEntity.class);
        taskEntity1.setCompleted(false);
        TaskEntity savedTaskEntity1 = taskRepository.save(taskEntity1);

//        TypeMap<TaskEntity, CreateTaskResponseDto> typeMapTaskEntityToDto = modelMapper.typeMap(TaskEntity.class, CreateTaskResponseDto.class);
//        typeMapTaskEntityToDto.addMappings(mapper -> {
//            mapper.skip(TaskEntity::getId, CreateTaskResponseDto::);
//        });
        CreateTaskResponseDto createTaskResponseDto1 = modelMapper.map(savedTaskEntity1, CreateTaskResponseDto.class);

//        TaskMapper taskMapper = TaskMapper.INSTANCE;
//        TaskEntity taskEntity = taskMapper.mapCreateTaskRequestDtoToTaskEntity(createTaskRequestDto);
//        taskEntity.setCompleted(false);
//
//        TaskEntity savedTaskEntity = taskRepository.save(taskEntity);
//
//        CreateTaskResponseDto createTaskResponseDto = taskMapper.mapTaskEntityToCreateTaskResponseDto(savedTaskEntity);

        return createTaskResponseDto1;
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
