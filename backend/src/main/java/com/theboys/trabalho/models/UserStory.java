package com.theboys.trabalho.models;

import lombok.Data;

import jakarta.persistence.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class UserStory{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private Integer relevance;

    @OneToMany
    private List<Task> tasks;

    @ManyToOne
    private Epic epic;

    @ManyToOne
    private UserStoryType userStoryType;
}
