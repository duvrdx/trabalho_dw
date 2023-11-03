package com.theboys.trabalho.models.type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.theboys.trabalho.models.Epic;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
@Table(name="epic_type")
public class EpicType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;

    @OneToMany(mappedBy = "epicType")
    @JsonBackReference
    private List<UserStoryType> userStoryTypeList;

    @OneToMany(mappedBy = "epicType")
    @JsonBackReference
    private List<Epic> epics;
}