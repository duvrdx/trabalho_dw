package com.theboys.trabalho.dto.type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.theboys.trabalho.models.type.EpicType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EpicTypeDTO {
    private UUID id;
    private String description;

    public EpicTypeDTO(EpicType epicType){
        this.description = epicType.getDescription();
        this.id = epicType.getId();
    }

    public EpicType build(){
        return new EpicType().setDescription(description);
    }
}
