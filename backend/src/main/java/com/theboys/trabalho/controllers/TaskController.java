package com.theboys.trabalho.controllers;

import com.theboys.trabalho.dto.TaskDTO;
import com.theboys.trabalho.dto.TaskTypeDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.services.TaskService;
import com.theboys.trabalho.services.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping()
    public ResponseEntity<List<Task>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable UUID id){
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
    public ResponseEntity<Task> create(@RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(service.create(taskDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<Task> update(@PathVariable UUID id, @RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(service.update(id, taskDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<TaskDTO> deleteById(@PathVariable UUID id){
        try {
            TaskDTO taskDTO = new TaskDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
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