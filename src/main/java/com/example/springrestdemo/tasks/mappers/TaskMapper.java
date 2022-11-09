package com.example.springrestdemo.tasks.mappers;

import com.example.springrestdemo.tasks.TaskEntity;
import com.example.springrestdemo.tasks.dtos.CreateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.CreateTaskResponseDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskRequestDto;
import com.example.springrestdemo.tasks.dtos.UpdateTaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskEntity mapCreateTaskRequestDtoToTaskEntity(CreateTaskRequestDto createTaskRequestDto);

    CreateTaskResponseDto mapTaskEntityToCreateTaskResponseDto(TaskEntity taskEntity);

    TaskEntity mapUpdateTaskRequestDtoToTaskEntity(UpdateTaskRequestDto updateTaskRequestDto, @MappingTarget TaskEntity taskEntity);

    UpdateTaskResponseDto mapTaskEntityToUpdateTaskResponseDto(TaskEntity taskEntity);
}
