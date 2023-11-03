package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.services.*;
import com.theboys.trabalho.services.type.TaskTypeService;
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
public class TaskDTO {
    @JsonIgnore
    private TaskTypeService taskTypeService;
    @JsonIgnore
    private UserStoryService usService;
    @JsonIgnore
    private TaskService taskService;

    @JsonIgnore
    private UUID id;
    private String description;
    private String title;

    // Foreign Keys
    private UUID userStoryId;
    private UUID taskTypeId;
    private List<UUID> dependencies;

    public TaskDTO(Task task){
        this.description = task.getDescription();
        this.title = task.getTitle();
        this.id = task.getId();
        this.userStoryId = task.getUserStory().getId();
        this.taskTypeId = task.getTaskType().getId();
        this.dependencies = new ArrayList<UUID>();

        if(!task.getDepends().isEmpty()){
            for(Task t: task.getDepends()){
                this.dependencies.add(t.getId());
            }
        }

    }

    public Task build(){
        List<Task> dependencies = new ArrayList<Task>();

        if(this.dependencies != null){
            for(UUID id: this.dependencies){
                dependencies.add(taskService.findById(id));
            }
        }

        return new Task().setDescription(description)
                .setTaskType(taskTypeService.findById(taskTypeId))
                .setUserStory(usService.findById(userStoryId))
                .setTitle(title)
                .setDepends(dependencies);
    }
}
