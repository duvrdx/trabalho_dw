package com.theboys.trabalho.dto;

import com.fasterxml.jackson.annotation.*;
import com.theboys.trabalho.models.*;
import com.theboys.trabalho.services.EpicService;
import com.theboys.trabalho.services.EpicTypeService;
import com.theboys.trabalho.services.ProjectService;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicDTO{

    // Precisa importar esses serviços pra poder usar na parte de build
    // esses services tem q ser importados no Controller e setados no DTO
    // olha a parte de POST e PUT
    @JsonIgnore
    private EpicTypeService epicTypeService;

    @JsonIgnore
    private ProjectService projectService;

    @JsonIgnore
    private EpicService epicService;

    @JsonIgnore
    private UUID id;
    private String title;
    private String description;
    private Integer relevance;

    private UUID epicTypeId;
    private UUID projectId;
    private List<UUID> dependencies;

    private EpicCategory category;

    public EpicDTO(Epic epic){
        this.description = epic.getDescription();
        this.relevance = epic.getRelevance();
        this.category = epic.getCategory();
        this.title = epic.getTitle();
        this.epicTypeId = epic.getEpicType().getId();
        this.projectId = epic.getProject().getId();
        this.dependencies = new ArrayList<UUID>();

        if(!epic.getDepends().isEmpty()){
            for(Epic e: epic.getDepends()){
                this.dependencies.add(e.getId());
            }
        }
        this.id = epic.getId();
    }

    public Epic build(){
        List<Epic> dependencies = new ArrayList<Epic>();

        if(this.dependencies != null){
            for(UUID id: this.dependencies){
                dependencies.add(epicService.findById(id));
            }
        }

        return new Epic()
                .setDescription(description)
                .setRelevance(relevance)
                .setTitle(title)
                .setEpicType(epicTypeService.findById(epicTypeId))
                .setCategory(category)
                .setDepends(dependencies)
                .setProject(projectService.findById(projectId));
    }
}
