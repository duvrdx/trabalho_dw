package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Epic{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private String title;
    private Integer relevance;

    @OneToMany
    private List<UserStory> userStoryList;

    @ManyToOne
    private EpicType epicType;
}
