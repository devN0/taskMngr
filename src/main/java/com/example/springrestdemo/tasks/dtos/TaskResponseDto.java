package com.example.springrestdemo.tasks.dtos;

import com.example.springrestdemo.notes.NoteEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TaskResponseDto {
    private Long id;
    @JsonProperty("Task Name")
    private String name;
    @JsonProperty("Due Date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
    private Boolean completed;
    private List<NoteEntity> notes;
}
