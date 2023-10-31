package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.models.UserStory;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryDTO {

    private String description;
    private String title;
    private Integer relevance;
    private Epic epic;
    private UserStoryType userStoryType;
    private List<Task> taskList;

    public UserStoryDTO(UserStory userStory){
        this.description = userStory.getDescription();
        this.title = userStory.getTitle();
        this.relevance = userStory.getRelevance();
        this.epic = userStory.getEpic();
        this.userStoryType = userStory.getUserStoryType();
        this.taskList = userStory.getTasks();
    }

    public UserStory build(){
        return new UserStory()
                .setDescription(description)
                .setTitle(title)
                .setRelevance(relevance)
                .setEpic(epic)
                .setUserStoryType(userStoryType)
                .setTasks(taskList);
    }
}
