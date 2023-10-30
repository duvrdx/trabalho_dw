package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.EpicType;
import com.theboys.trabalho.models.TaskType;
import com.theboys.trabalho.repositories.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeRepository repository;

    public TaskType create(TaskType taskType){
        repository.save(taskType);
        return taskType;
    }

    public List<TaskType> findAll(){
        return repository.findAll();
    }

    public TaskType findById(UUID id){
        for(TaskType taskType : this.findAll()){
            if(taskType.getId().equals(id)) return taskType;
        }
        throw new EpicNotFoundException();
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public TaskType update(UUID id, TaskType newTaskType){
        TaskType outDatedTaskType = findById(id);
        outDatedTaskType.setDescription(newTaskType.getDescription());
        repository.save(outDatedTaskType);

        return newTaskType;
    }
}
