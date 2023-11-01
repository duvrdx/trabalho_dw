package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "project")
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "project")
    private List<Epic> epics;
}