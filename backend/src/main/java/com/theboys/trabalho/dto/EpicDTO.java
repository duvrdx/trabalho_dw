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
    private String description;
    private String title;
    private Integer relevance;
    private EpicType epicType;

    public EpicDTO(Epic epic){
        this.description = epic.getDescription();
        this.relevance = epic.getRelevance();;
    }

    public Epic build(){
        return new Epic()
                .setDescription(description)
                .setRelevance(relevance)
                .setTitle(title)
                .setEpicType(epicType);
    }
}
