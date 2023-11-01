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

    @Enumerated(EnumType.STRING)
    private UserStoryCategory category;

    @OneToMany(mappedBy = "userStory")
    private List<Task> tasks;

    @ManyToOne
    private Epic epic;

    @ManyToOne
    private UserStoryType userStoryType;

    @ManyToMany
    @JoinTable(name = "us_dependencies",
            joinColumns = @JoinColumn(name = "us_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    private List<UserStory> depends;
}
