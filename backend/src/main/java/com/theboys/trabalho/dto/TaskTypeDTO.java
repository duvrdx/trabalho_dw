package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import com.theboys.trabalho.services.TaskTypeService;
import com.theboys.trabalho.services.UserStoryService;
import com.theboys.trabalho.services.UserStoryTypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TaskTypeDTO {
    @JsonIgnore
    private UserStoryTypeService usTypeService;

    @JsonIgnore
    private UUID id;
    private String description;
    private UUID userStoryTypeId;

    public TaskTypeDTO(TaskType taskType){
        this.description = taskType.getDescription();
        this.userStoryTypeId = taskType.getUserStoryType().getId();
        this.id = taskType.getId();
    }

    public TaskType build(){
        return new TaskType()
                .setDescription(description)
                .setUserStoryType(usTypeService.findById(userStoryTypeId));
    }
}
