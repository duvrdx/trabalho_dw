package com.theboys.trabalho.services.type;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.type.UserStoryType;
import com.theboys.trabalho.repositories.type.UserStoryTypeRepository;
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
        outdatedUserStoryType.setDescription(newUserStoryType.getDescription());
        repository.save(outdatedUserStoryType);

        return newUserStoryType;
    }
}
