package com.theboys.trabalho.dto.type;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.type.UserStoryType;
import com.theboys.trabalho.services.type.EpicTypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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