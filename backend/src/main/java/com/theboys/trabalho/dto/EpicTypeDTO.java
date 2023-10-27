package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.EpicType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicTypeDTO {
    private String description;

    public EpicTypeDTO(EpicType epicType){
        this.description = epicType.getDescription();
    }

    public EpicType build(){
        return new EpicType().setDescription(description);
    }
}
