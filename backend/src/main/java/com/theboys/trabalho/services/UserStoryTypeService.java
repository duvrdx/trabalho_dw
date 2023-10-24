package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.repositories.UserStoryTypeRepository;
import com.theboys.trabalho.models.UserStoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserStoryTypeService{
    @Autowired
    private UserStoryTypeRepository repository;

    public UserStoryType create(UserStoryType userStoryType){
        repository.save(userStoryType);
        return userStoryType;
    }

    public List<UserStoryType> findAll(){
        return repository.findAll();
    }

    public UserStoryType findById(UUID id){
        for(UserStoryType userStoryType : this.findAll()){
            if(userStoryType.getId().equals(id)) return userStoryType;
        }
        throw new EpicNotFoundException();
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public UserStoryType update(UUID id, UserStoryType newUserStoryType){
        UserStoryType outdatedUserStoryType = findById(id);
        outdatedUserStoryType.setName(newUserStoryType.getName());
        repository.save(outdatedUserStoryType);

        return newUserStoryType;
    }
}
