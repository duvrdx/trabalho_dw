package com.theboys.trabalho.controllers.type;

import com.theboys.trabalho.dto.type.EpicTypeDTO;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.type.EpicType;
import com.theboys.trabalho.services.type.EpicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/epicType")
public class EpicTypeController{
    @Autowired
    private EpicTypeService service;

    @GetMapping()
    public ResponseEntity<List<EpicType>> findAll(){
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpicType> getById(@PathVariable UUID id){
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
    public ResponseEntity<EpicType> create(@RequestBody EpicTypeDTO epicTypeDTO){
        try {
            return new ResponseEntity<>(service.create(epicTypeDTO.build()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<EpicType> update(@PathVariable UUID id, @RequestBody EpicTypeDTO epicTypeDTO){
        try {
            return new ResponseEntity<>(service.update(id, epicTypeDTO.build()), HttpStatus.OK);
        }catch (EpicNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<EpicTypeDTO> deleteById(@PathVariable UUID id){
        try {
            EpicTypeDTO epicTypeDTO = new EpicTypeDTO(service.findById(id));
            service.delete(id);
            return new ResponseEntity<>(epicTypeDTO, HttpStatus.OK);
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