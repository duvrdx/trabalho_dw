package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTypeDTO {
    private String description;

    public TaskTypeDTO(TaskType taskType){
        this.description = taskType.getDescription();
    }

    public TaskType build(){
        return new TaskType().setDescription(description);
    }
}
