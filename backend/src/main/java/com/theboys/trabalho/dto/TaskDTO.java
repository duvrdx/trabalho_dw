package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TaskDTO {
    private UUID id;
    private String description;
    private String title;

    public TaskDTO(Task task){
        this.description = task.getDescription();
        this.title = task.getTitle();
        this.id = task.getId();
    }

    public Task build(){
        return new Task().setDescription(description);
    }
}
