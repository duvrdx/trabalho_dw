package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Task {
    @Id
    @GeneratedValue
    private UUID id; // Utilizando UUID como identificador

    private String name;

    @ManyToOne
    private TaskType taskType;

    @ManyToOne
    private UserStory userStory;

    @OneToMany(mappedBy = "predecessor")
    private List<Precedence> predecessorOf;

    @OneToMany(mappedBy = "successor")
    private List<Precedence> successorOf;
}
