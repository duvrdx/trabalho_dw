package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Project;
import com.theboys.trabalho.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private EpicService epicService;

    public Project create(Project project){
        repository.save(project);
        return project;
    }

    public List<Project> findAll(){
        return repository.findAll();
    }

    public Project findById(UUID id){
        for(Project epic : findAll()){
            if(epic.getId().equals(id)) return epic;
        }
        throw new EpicNotFoundException();
    }

    public Project update(UUID id, Project newProject){
        Project outdatedProject = findById(id);
        outdatedProject.setName(newProject.getName());
        repository.save(outdatedProject);

        return newProject;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }
}
