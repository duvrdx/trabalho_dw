package com.theboys.trabalho.services;

// import aula25_grafos.Grafo;
import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.models.UserStory;
import com.theboys.trabalho.models.type.EpicType;
import com.theboys.trabalho.models.type.TaskType;
import com.theboys.trabalho.models.type.UserStoryType;
import com.theboys.trabalho.repositories.EpicRepository;
import com.theboys.trabalho.services.type.EpicTypeService;
import com.theboys.trabalho.services.type.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.UUID;

@Service
public class EpicService {


    // private final Grafo<Epic> graph = new Grafo<Epic>();

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


        // Adicionar dependencia

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

        if (matcher.find()){ actor = matcher.group(1);}
        item = palavras[palavras.length - 1];

        for(UserStoryType usType: userStoryTypes) {
            UserStory userStory = new UserStory();

            action = usType.getDescription().toLowerCase();

            userStory
                    .setEpic(epic)
                    .setUserStoryType(usType)
                    .setRelevance(epic.getRelevance())
                    .setTitle(epic.getTitle())
                    .setDescription(String.format("Eu, como %s, quero %s um %s", actor, action, item));


            usService.create(userStory);

            System.out.println(usType.getTaskTypeList().size());

            for(TaskType taskType: usType.getTaskTypeList()){
                Task task = new Task();
                defaultTask = taskType.getDescription();

                task.setUserStory(userStory)
                        .setDescription(String.format("%s %s", defaultTask, item))
                        .setTitle(epic.getTitle())
                        .setTaskType(taskType);

                taskService.create(task);
            }
        }
    }



}
