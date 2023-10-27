package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String name;

    public ProjectDTO(Project project){
        this.name = project.getName();
    }

    public Project build(){
        return new Project().setName(name);
    }
}