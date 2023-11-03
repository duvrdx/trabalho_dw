package com.theboys.trabalho.dto.type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.type.TaskType;
import com.theboys.trabalho.services.type.UserStoryTypeService;
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

//    @JsonIgnore
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
