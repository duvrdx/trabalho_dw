package com.theboys.trabalho.dto;

import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.UserStoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicDTO{
    private String name;

    public EpicDTO(Epic epic){
        name = epic.getName();
    }

    public Epic build(){
        return new Epic().setName(name);
    }
}
