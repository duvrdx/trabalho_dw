package com.theboys.trabalho.models;

import lombok.Data;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class UserStory{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private Integer relevance;

    @OneToMany
    private List<Task> tasks;

    @ManyToOne
    private UserStoryType userStoryType;
}
