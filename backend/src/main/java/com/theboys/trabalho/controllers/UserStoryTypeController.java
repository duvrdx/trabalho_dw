package com.theboys.trabalho.controllers;

import com.theboys.trabalho.dto.EpicDTO;
import com.theboys.trabalho.dto.UserStoryTypeDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.UserStoryType;
import com.theboys.trabalho.services.EpicService;
import com.theboys.trabalho.services.UserStoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/userstorytype")
public class UserStoryTypeController {
    @Autowired
    private UserStoryTypeService service;

    @GetMapping()
    public ResponseEntity<List<UserStoryType>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStoryType> getById(@PathVariable UUID id){
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
    public ResponseEntity<UserStoryType> create(@RequestBody UserStoryTypeDTO userStoryTypeDTO){
        try {
            return new ResponseEntity<>(service.create(userStoryTypeDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<UserStoryType> update(@PathVariable UUID id, @RequestBody UserStoryTypeDTO userStoryTypeDTO){
        try {
            return new ResponseEntity<>(service.update(id, userStoryTypeDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<UserStoryTypeDTO> deleteById(@PathVariable UUID id){
        try {
            UserStoryTypeDTO userStoryTypeDTO = new UserStoryTypeDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(userStoryTypeDTO , HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
