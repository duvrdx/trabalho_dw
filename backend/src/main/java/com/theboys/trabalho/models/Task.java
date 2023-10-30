package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;


@Entity
@Data
@Accessors(chain = true)
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;

    private String title;

    @ManyToOne
    private UserStory userStory;

    @ManyToOne
    private TaskType taskType;
}
