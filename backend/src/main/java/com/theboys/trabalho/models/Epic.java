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

    @Enumerated(EnumType.STRING)
    private UserStoryCategory category;

    @OneToMany(mappedBy = "epic")
    private List<UserStory> userStoryList;

    @ManyToOne
    private EpicType epicType;

    @ManyToOne
    @JoinColumn(name="project.id")
    private Project project;

    @ManyToMany
    @JoinTable(name = "epic_dependencies",
            joinColumns = @JoinColumn(name = "epic_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    private List<Epic> depends;
}
