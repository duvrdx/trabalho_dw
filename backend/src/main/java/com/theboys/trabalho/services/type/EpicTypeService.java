package com.theboys.trabalho.services.type;

import com.theboys.trabalho.exceptions.EpicNotFoundException;
import com.theboys.trabalho.models.type.EpicType;
import com.theboys.trabalho.repositories.type.EpicTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EpicTypeService {
    @Autowired
    private EpicTypeRepository repository;

    public EpicType create(EpicType epicType){
        repository.save(epicType);
        return epicType;
    }

    public List<EpicType> findAll(){
        return repository.findAll();
    }

    public EpicType findById(UUID id){
        for(EpicType epicType : this.findAll()){
            if(epicType.getId().equals(id)) return epicType;
        }
        throw new EpicNotFoundException();
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public EpicType update(UUID id, EpicType newEpicType){
        EpicType outdatedEpicType = findById(id);
        outdatedEpicType.setDescription(newEpicType.getDescription());
        repository.save(outdatedEpicType);

        return newEpicType;
    }
}
