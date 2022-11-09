package com.example.springrestdemo.tasks;

import com.example.springrestdemo.notes.NoteEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dueDate;
    private Boolean completed;
    @OneToMany
    private List<NoteEntity> notes;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
}
