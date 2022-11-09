package com.example.springrestdemo.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskRequestDto {
    @JsonProperty("Task Name")
    private String name;
    @JsonProperty("Due Date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
}
