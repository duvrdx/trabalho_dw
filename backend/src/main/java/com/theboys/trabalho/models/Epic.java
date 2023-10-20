package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Epic {
    @Id
    @GeneratedValue
    private UUID id; // Utilizando UUID como identificador

    private String name;

    @ManyToOne
    private UserStoryType userStoryType;

    @OneToMany(mappedBy = "epic")
    private List<UserStory> userStories;
}