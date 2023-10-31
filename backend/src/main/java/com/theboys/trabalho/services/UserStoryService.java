package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.UserStory;
import com.theboys.trabalho.models.UserStoryType;
import com.theboys.trabalho.repositories.UserStoryRepository;
import com.theboys.trabalho.repositories.UserStoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserStoryService {
    @Autowired
    private UserStoryRepository repository;

    public UserStory create(UserStory userStory){
        repository.save(userStory);
        return userStory;
    }

    public List<UserStory> findAll(){
        return repository.findAll();
    }

    public UserStory findById(UUID id){
        for(UserStory userStory : this.findAll()){
            if(userStory.getId().equals(id))
                return userStory;
        }
        throw new EpicNotFoundException();
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public UserStory update(UUID id, UserStory newUserStory){
        UserStory outdatedUserStory = findById(id);
        outdatedUserStory.setDescription(newUserStory.getDescription());
        repository.save(outdatedUserStory);

        return newUserStory;
    }
}
