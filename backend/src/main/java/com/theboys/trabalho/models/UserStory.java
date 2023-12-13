package com.theboys.trabalho.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.category.UserStoryCategory;
import com.theboys.trabalho.models.type.UserStoryType;
import lombok.Data;

import jakarta.persistence.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private Integer relevance;

    @Enumerated(EnumType.STRING)
    private UserStoryCategory category;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "epic_id")
    @JsonManagedReference
    private Epic epic;

    @ManyToOne
    @JoinColumn(name = "user_story_type_id")
    @JsonManagedReference
    private UserStoryType userStoryType;

    @ManyToMany
    @JoinTable(name = "us_dependencies",
            joinColumns = @JoinColumn(name = "us_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    @JsonManagedReference
    private List<UserStory> depends;
}