package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;


@Data
@Entity
@Accessors(chain = true)
public class UserStoryType {
    @Id
    @GeneratedValue
    private UUID id; // Utilizando UUID como identificador

    private String name;

    @OneToMany
    private List<TaskType> defaultTasks;
}