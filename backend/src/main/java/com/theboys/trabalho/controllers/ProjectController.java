package com.theboys.trabalho.controllers;

import com.theboys.trabalho.dto.ProjectDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Project;
import com.theboys.trabalho.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController{
    @Autowired
    private ProjectService service;

    @GetMapping()
    public ResponseEntity<List<Project>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable UUID id){
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Project> create(@RequestBody ProjectDTO projectDTO){
        try {
            return new ResponseEntity<>(service.create(projectDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<Project> update(@PathVariable UUID id, @RequestBody ProjectDTO projectDTO){
        try {
            return new ResponseEntity<>(service.update(id, projectDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<ProjectDTO> deleteById(@PathVariable UUID id){
        try {
            ProjectDTO projectDTO = new ProjectDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}