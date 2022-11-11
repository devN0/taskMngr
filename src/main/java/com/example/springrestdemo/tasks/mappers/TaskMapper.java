package com.example.springrestdemo.tasks.mappers;

import com.example.springrestdemo.tasks.TaskEntity;
import com.example.springrestdemo.tasks.dtos.CreateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.TaskResponseDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskEntity mapCreateTaskRequestDtoToTaskEntity(CreateTaskRequestDto createTaskRequestDto);

    TaskResponseDto mapTaskEntityToTaskResponseDto(TaskEntity taskEntity);

    TaskEntity mapUpdateTaskRequestDtoToTaskEntity(UpdateTaskRequestDto updateTaskRequestDto, @MappingTarget TaskEntity taskEntity);
}
