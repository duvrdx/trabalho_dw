package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.EpicType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicDTO{
    private String name;
    private Integer relevance;

    public EpicDTO(Epic epic){
        this.name = epic.getName();
        this.relevance = epic.getRelevance();;
    }

    public Epic build(){
        return new Epic().setName(name).setRelevance(relevance);
    }
}
