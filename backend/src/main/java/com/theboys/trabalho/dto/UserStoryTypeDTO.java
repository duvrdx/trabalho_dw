package com.theboys.trabalho.dto;
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
public class UserStoryTypeDTO{
    private String name;

    public UserStoryTypeDTO(UserStoryType userStoryType){
        name = userStoryType.getName();
    }

    public UserStoryType build(){
        return new UserStoryType().setName(name)
                .setDefaultTasks(new ArrayList<String>());
    }
}