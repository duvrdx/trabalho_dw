package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TaskTypeDTO {
    private UUID id;
    private String description;
    private UserStoryType userStoryType;

    public TaskTypeDTO(TaskType taskType){
        this.description = taskType.getDescription();
        this.userStoryType = taskType.getUserStoryType();
        this.id = taskType.getId();
    }

    public TaskType build(){
        return new TaskType()
                .setDescription(description)
                .setUserStoryType(userStoryType);
    }
}
