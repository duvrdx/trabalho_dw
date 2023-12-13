package com.theboys.trabalho.controllers;

import com.theboys.trabalho.dto.ProjectDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Project;
import com.theboys.trabalho.models.Relatory;
import com.theboys.trabalho.services.RelatoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/relatory")
public class RelatoryController {
    @Autowired
    private RelatoryService service;

    @GetMapping("/{type}")
    public ResponseEntity<Object> getById(@PathVariable String type){
        try {
            return new ResponseEntity<>(service.getRelatory(type), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}