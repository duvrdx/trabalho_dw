package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.UserStory;
import com.theboys.trabalho.services.*;
import com.theboys.trabalho.services.type.UserStoryTypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserStoryDTO {
    @JsonIgnore
    private UserStoryTypeService usTypeService;
    @JsonIgnore
    private UserStoryService usService;
    @JsonIgnore
    private EpicService epicService;

    @JsonIgnore
    private UUID id;
    private String description;
    private String title;
    private Integer relevance;
    private UUID epicId;
    private UUID userStoryTypeId;

    public UserStoryDTO(UserStory userStory){
        this.description = userStory.getDescription();
        this.title = userStory.getTitle();
        this.relevance = userStory.getRelevance();
        this.epicId = userStory.getEpic().getId();
        this.userStoryTypeId = userStory.getUserStoryType().getId();
        this.id = userStory.getId();
    }

    public UserStory build(){
        return new UserStory()
                .setDescription(description)
                .setTitle(title)
                .setRelevance(relevance)
                .setEpic(epicService.findById(epicId))
                .setUserStoryType(usTypeService.findById(userStoryTypeId));
    }
}
