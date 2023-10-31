package com.theboys.trabalho.dto;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryTypeDTO {
    private String description;
    private EpicType epicType;
    private List<TaskType> taskTypeList;

    public UserStoryTypeDTO(UserStoryType userStoryType){
        this.description = userStoryType.getDescription();
        this.taskTypeList = userStoryType.getTaskTypeList();
        this.epicType = userStoryType.getEpicType();
    }

    public UserStoryType build(){
        return new UserStoryType()
                .setDescription(description)
                .setTaskTypeList(taskTypeList)
                .setEpicType(epicType);
    }
}