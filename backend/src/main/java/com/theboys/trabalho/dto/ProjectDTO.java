package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectDTO {
//    @JsonIgnore
    private UUID id;
    private String name;

    public ProjectDTO(Project project){
        this.name = project.getName();
        this.id = project.getId();
    }
    public Project build(){
        return new Project().setName(name);
    }
}