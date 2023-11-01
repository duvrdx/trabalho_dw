package com.theboys.trabalho.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private String title;
    private Integer relevance;

    @Enumerated(EnumType.STRING)
    private EpicCategory category;

    @OneToMany(mappedBy = "epic")
    @JsonBackReference
    private List<UserStory> userStories;

    @ManyToOne
    @JoinColumn(name = "epic_type_id")
    @JsonManagedReference
    private EpicType epicType;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonManagedReference
    private Project project;

    @ManyToMany
    @JoinTable(name = "epic_dependencies",
            joinColumns = @JoinColumn(name = "epic_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"))
    @JsonManagedReference
    private List<Epic> depends;
}