package com.example.springrestdemo.tasks.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskRequestDto {
    @JsonProperty("Task Name")
    @NonNull
    private String name;
    @JsonProperty("Due Date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NonNull
    private LocalDate dueDate;
}
