package com.theboys.trabalho.dto;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryTypeDTO {
    private String name;

    public UserStoryTypeDTO(UserStoryType userStoryType){
        name = userStoryType.getDescription();
    }

    public UserStoryType build(){
        return new UserStoryType().setDescription(name)
                .setTaskType(new ArrayList<TaskType>());
    }
}