package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTypeDTO {

    private String description;
    private UserStoryType userStoryType;

    public TaskTypeDTO(TaskType taskType){
        this.description = taskType.getDescription();
        this.userStoryType = taskType.getUserStoryType();
    }

    public TaskType build(){
        return new TaskType()
                .setDescription(description)
                .setUserStoryType(userStoryType);
    }
}
