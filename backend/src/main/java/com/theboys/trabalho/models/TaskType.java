package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class TaskType{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;

    @ManyToOne
    private UserStoryType userStoryType;

}
