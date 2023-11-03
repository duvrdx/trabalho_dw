package com.theboys.trabalho.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import com.theboys.trabalho.services.EpicService;
import com.theboys.trabalho.services.EpicTypeService;
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
    @JsonIgnore
    private EpicTypeService epicTypeService;

    @JsonIgnore
    private UUID id;
    private String description;
    private UUID epicTypeId;

    public UserStoryTypeDTO(UserStoryType userStoryType){
        this.description = userStoryType.getDescription();
        this.epicTypeId = userStoryType.getEpicType().getId();
        this.id = userStoryType.getId();
    }

    public UserStoryType build(){
        return new UserStoryType()
                .setDescription(description)
                .setEpicType(epicTypeService.findById(epicTypeId));
    }
}