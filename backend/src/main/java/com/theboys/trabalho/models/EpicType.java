package com.theboys.trabalho.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
@Table(name="epic_type")
public class EpicType{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;

    @OneToMany(mappedBy = "epicType")
    private List<UserStoryType> userStoryType;
}
