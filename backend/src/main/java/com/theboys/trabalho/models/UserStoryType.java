package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class UserStoryType{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;

    @ManyToOne
    private EpicType epicType;

    @OneToMany
    //(mappedBy = "userStoryType")
    private List<TaskType> taskTypeList;
}
