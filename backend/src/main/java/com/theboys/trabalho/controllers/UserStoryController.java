package com.theboys.trabalho.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theboys.trabalho.dto.UserStoryDTO;
import com.theboys.trabalho.dto.UserStoryTypeDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.UserStory;
import com.theboys.trabalho.models.UserStoryType;
import com.theboys.trabalho.services.EpicService;
import com.theboys.trabalho.services.UserStoryService;
import com.theboys.trabalho.services.UserStoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/userStory")
public class UserStoryController {
    @Autowired
    private UserStoryService service;
    @Autowired
    private UserStoryTypeService usTypeService;
    @Autowired
    private EpicService epicService;

    @GetMapping()
    public ResponseEntity<List<UserStory>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStory> getById(@PathVariable UUID id){
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
    public ResponseEntity<UserStory> create(@RequestBody UserStoryDTO userStoryDTO){
        try {
            userStoryDTO.setEpicService(epicService);
            userStoryDTO.setUsTypeService(usTypeService);
            return new ResponseEntity<>(service.create(userStoryDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<UserStory> update(@PathVariable UUID id, @RequestBody UserStoryDTO userStoryDTO){
        try {
            userStoryDTO.setEpicService(epicService);
            userStoryDTO.setUsTypeService(usTypeService);
            return new ResponseEntity<>(service.update(id, userStoryDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<UserStoryDTO> deleteById(@PathVariable UUID id){
        try {
            UserStoryDTO userStoryDTO = new UserStoryDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(userStoryDTO , HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
