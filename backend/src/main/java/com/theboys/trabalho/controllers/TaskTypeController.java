package com.theboys.trabalho.controllers;

import com.theboys.trabalho.dto.EpicTypeDTO;
import com.theboys.trabalho.dto.TaskTypeDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.models.UserStoryType;
import com.theboys.trabalho.services.EpicTypeService;
import com.theboys.trabalho.services.TaskTypeService;
import com.theboys.trabalho.services.UserStoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/taskType")
public class TaskTypeController {
    @Autowired
    private TaskTypeService service;

    @Autowired
    private UserStoryTypeService usTypeService;

    @GetMapping()
    public ResponseEntity<List<TaskType>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskType> getById(@PathVariable UUID id){
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
    public ResponseEntity<TaskType> create(@RequestBody TaskTypeDTO taskTypeDTO){
        try {
            taskTypeDTO.setUsTypeService(usTypeService);
            return new ResponseEntity<>(service.create(taskTypeDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<TaskType> update(@PathVariable UUID id, @RequestBody TaskTypeDTO taskTypeDTO){
        try {
            taskTypeDTO.setUsTypeService(usTypeService);
            return new ResponseEntity<>(service.update(id, taskTypeDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<TaskTypeDTO> deleteById(@PathVariable UUID id){
        try {
            TaskTypeDTO taskTypeDTO = new TaskTypeDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(taskTypeDTO, HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/{epicId}/addType/{epicTypeId}")
//    public ResponseEntity<EpicType> addUserStoryType(@PathVariable UUID epicId, @PathVariable UUID epicTypeId){
//        try {
//            return new ResponseEntity<>(service.addUserStoryType(epicId, epicTypeId), HttpStatus.OK);
//        }catch (EpicNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}