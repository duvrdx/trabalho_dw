package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task create(Task task){
        repository.save(task);
        return task;
    }

    public List<Task> findAll(){
        return repository.findAll();
    }

    public Task findById(UUID id){
        for(Task task : this.findAll()){
            if(task.getId().equals(id)) return task;
        }
        throw new EpicNotFoundException();
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public Task update(UUID id, Task newTask){
        Task outdatedTask = findById(id);
        if(newTask.getDepends().contains(outdatedTask)) throw new RuntimeException("Self dependency is not allowed");
        outdatedTask.setDescription(newTask.getDescription());

        // Adicionar dependencia

        repository.save(outdatedTask);

        return newTask;
    }
}
