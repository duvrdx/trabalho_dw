package com.theboys.trabalho.controllers;

import com.theboys.trabalho.dto.EpicDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.services.EpicService;
import com.theboys.trabalho.services.type.EpicTypeService;
import com.theboys.trabalho.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/epic")
public class EpicController{
    @Autowired
    private EpicService service;

    @Autowired
    private EpicTypeService epicTypeService;
    @Autowired
    private ProjectService projectService;


    @GetMapping()
    public ResponseEntity<List<Epic>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epic> getById(@PathVariable UUID id){
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
    public ResponseEntity<Epic> create(@RequestBody EpicDTO epicDTO){
        try {
            epicDTO.setEpicTypeService(epicTypeService);
            epicDTO.setProjectService(projectService);
            epicDTO.setEpicService(service);
            return new ResponseEntity<>(service.create(epicDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<Epic> update(@PathVariable UUID id, @RequestBody EpicDTO epicDTO){
        try {
            epicDTO.setEpicTypeService(epicTypeService);
            epicDTO.setProjectService(projectService);
            epicDTO.setEpicService(service);
            return new ResponseEntity<>(service.update(id, epicDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<EpicDTO> deleteById(@PathVariable UUID id){
        try {
            EpicDTO epicDTO = new EpicDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(epicDTO, HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}