package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String description;
    private String title;

    public TaskDTO(Task task){
        this.description = task.getDescription();
        this.title = task.getTitle();
    }

    public Task build(){
        return new Task();
        //Não consegui seguir o padrão de setDescription()
    }
}
