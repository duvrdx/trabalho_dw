package com.theboys.trabalho.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.type.TaskType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    @JsonManagedReference
    private UserStory userStory;

    @ManyToOne
    @JoinColumn(name = "task_type_id")
    @JsonManagedReference
    private TaskType taskType;

    @ManyToMany
    @JoinTable(name = "task_dependencies",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    @JsonManagedReference
    private List<Task> depends;
}
