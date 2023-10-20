package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.repositories.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EpicService{

    @Autowired
    private EpicRepository repository;

    public Epic create(Epic epic){
        repository.save(epic);
        return epic;
    }

    public List<Epic> findAll(){
        return repository.findAll();
    }

    public Epic findById(UUID id){
        for(Epic epic : findAll()){
            if(epic.getId().equals(id)) return epic;
        }
        throw new EpicNotFoundException();
    }

    public Epic update(UUID id, Epic newEpic){
        Epic outdatedEpic = findById(id);
        outdatedEpic.setName(newEpic.getName());
        repository.save(outdatedEpic);

        return newEpic;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }
}
