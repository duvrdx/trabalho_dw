package com.theboys.trabalho.services;
import aula25_grafos.Grafo;
import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Task;
import com.theboys.trabalho.models.UserStory;
import lib.ArvoreAVLExemplo;
import lib.ArvoreBinariaExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RelatoryService {

    private final Comparator<Epic> epicComparator = new Comparator<Epic>() {
        public int compare(Epic o1, Epic o2) {
            if(o1.getDepends().contains(o2)){
                return -1;
            }

            if(o2.getId().equals(o1.getId())){
                return 0;
            }

            return 1;
        }
    };
    private final Comparator<Task> taskComparator = new Comparator<Task>() {
        public int compare(Task o1, Task o2) {
            if(o1.getDepends().contains(o2)){
                return -1;
            }

            if(o2.getId().equals(o1.getId())){
                return 0;
            }

            return 1;
        }
    };
    private final Comparator<UserStory> userStoryComparator = new Comparator<UserStory>() {
        public int compare(UserStory o1, UserStory o2) {
            if(o1.getDepends().contains(o2)){
                return -1;
            }

            if(o2.getId().equals(o1.getId())){
                return 0;
            }

            return 1;
        }
    };
    @Autowired
    private EpicService epicService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserStoryService userStoryService;

    public List<Epic> getEpicOrder(){
        ArvoreBinariaExemplo<Epic> tree = new ArvoreBinariaExemplo<Epic>(epicComparator);

        for(Epic epic : epicService.findAll()){
            tree.adicionar(epic);
        }

        return tree.caminharEmNivel();
    }

    public Boolean checkEpicCycle(){
        Grafo<String> graph = new Grafo<String>();
        for(Epic destination : epicService.findAll()){
            graph.adicionarVertice(destination.getTitle());
            for(Epic origin : destination.getDepends()){
                graph.adicionarAresta(origin.getTitle(), destination.getTitle());
            }
        }

        return graph.temCiclo();
    }

    public List<Task> getTaskOrder(){
        ArvoreBinariaExemplo<Task> tree = new ArvoreBinariaExemplo<Task>(taskComparator);

        for(Task task : taskService.findAll()){
            tree.adicionar(task);
        }

        return tree.caminharEmNivel();
    }

    public Boolean checkTaskCycle(){
        Grafo<Task> graph = new Grafo<Task>();
        for(Task destination : taskService.findAll()){
            graph.adicionarVertice(destination);
            for(Task origin : destination.getDepends()){
                graph.adicionarAresta(origin, destination);
            }
        }

        return graph.temCiclo();
    }

    public List<UserStory> getUserStoryOrder(){
        ArvoreBinariaExemplo<UserStory> tree = new ArvoreBinariaExemplo<UserStory>(userStoryComparator);

        for(UserStory userStory : userStoryService.findAll()){
            tree.adicionar(userStory);
        }

        return tree.caminharEmNivel();
    }

    public Boolean checkUserStoryCycle(){
        Grafo<UserStory> graph = new Grafo<UserStory>();
        for(UserStory destination : userStoryService.findAll()){
            graph.adicionarVertice(destination);
            for(UserStory origin : destination.getDepends()){
                graph.adicionarAresta(origin, destination);
            }
        }

        return graph.temCiclo();
    }

}
