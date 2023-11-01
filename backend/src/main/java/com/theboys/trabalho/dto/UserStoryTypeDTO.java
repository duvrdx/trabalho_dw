package com.theboys.trabalho.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserStoryTypeDTO {
    private UUID id;
    private String description;
    private EpicType epicType;
    private List<TaskType> taskTypeList;

    public UserStoryTypeDTO(UserStoryType userStoryType){
        this.description = userStoryType.getDescription();
        this.taskTypeList = userStoryType.getTaskTypeList();
        this.epicType = userStoryType.getEpicType();
        this.id = userStoryType.getId();
    }

    public UserStoryType build(){
        return new UserStoryType()
                .setDescription(description)
                .setTaskTypeList(taskTypeList)
                .setEpicType(epicType);
    }
}