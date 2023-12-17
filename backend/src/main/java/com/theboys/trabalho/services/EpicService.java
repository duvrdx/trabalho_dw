package com.theboys.trabalho.services;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.models.UserStory;
import com.theboys.trabalho.models.type.TaskType;
import com.theboys.trabalho.models.type.UserStoryType;
import com.theboys.trabalho.repositories.EpicRepository;
import com.theboys.trabalho.services.type.EpicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.UUID;

@Service
public class EpicService {
    @Autowired
    private EpicRepository repository;

    @Autowired
    private EpicTypeService epicTypeService;

    @Autowired
    private UserStoryService usService;

    @Autowired
    private TaskService taskService;

    public Epic create(Epic epic){

        if(epic.getDepends().contains(epic)) throw new RuntimeException("Self dependency is not allowed");
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

        /// Função
        if(newEpic.getDepends().contains(outdatedEpic)) throw new RuntimeException("Self dependency is not allowed");
        outdatedEpic.setDescription(newEpic.getDescription());
        outdatedEpic.setTitle(newEpic.getTitle());
        outdatedEpic.setRelevance(newEpic.getRelevance());
        outdatedEpic.setEpicType(newEpic.getEpicType());
        outdatedEpic.setProject(newEpic.getProject());
        outdatedEpic.setDepends(newEpic.getDepends());

        repository.save(outdatedEpic);

        return newEpic;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public void generateUserStory(UUID id){
        Epic epic = this.findById(id);
        List<UserStoryType> userStoryTypes = epic.getEpicType().getUserStoryTypeList();

        Pattern pattern = Pattern.compile("como\\s+([^,\\s]+)");
        Matcher matcher = pattern.matcher(epic.getDescription());
        String[] palavras = epic.getDescription().split("\\s+");

        String actor = "Ator";
        String defaultTask;
        String action;
        String item;

        List<Epic> dependencies = epic.getDepends();
        List<Epic> validDependencies = new ArrayList<Epic>();

        if(!dependencies.isEmpty()){
            for(Epic dependency : dependencies) {
                if (!dependency.getUserStories().isEmpty()) {
                    validDependencies.add(dependency);
                }
            }
        }

        if (matcher.find()){ actor = matcher.group(1);}
        item = palavras[palavras.length - 1];

        for(UserStoryType usType: userStoryTypes) {
            UserStory userStory = new UserStory();
            List<UserStory> usDependencies = new ArrayList<UserStory>();

            action = usType.getDescription().toLowerCase();

            for(Epic validDependecy : validDependencies){
                usDependencies.addAll(validDependecy.getUserStories());
            }

            userStory
                    .setEpic(epic)
                    .setUserStoryType(usType)
                    .setRelevance(epic.getRelevance())
                    .setTitle(epic.getTitle())
                    .setDescription(String.format("Eu, como %s, quero %s um %s", actor, action, item))
                    .setDepends(usDependencies);



            usService.create(userStory);

            System.out.println(usType.getTaskTypeList().size());

            for(TaskType taskType: usType.getTaskTypeList()){
                Task task = new Task();
                defaultTask = taskType.getDescription();
                List<Task> taskDependencies = new ArrayList<Task>();

                for(UserStory usDependency : usDependencies){
                    taskDependencies.addAll(usDependency.getTasks());
                }

                task.setUserStory(userStory)
                        .setDescription(String.format("%s %s", defaultTask, item))
                        .setTitle(epic.getTitle())
                        .setTaskType(taskType)
                        .setDepends(taskDependencies);

                taskService.create(task);


            }
        }
    }



}