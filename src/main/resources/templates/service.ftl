package com.lizatechnology.generated.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lizatechnology.generated.entities.${entityName};
import com.lizatechnology.generated.repositories.${entityName}Repository;

@Service
public class ${entityName}Service {
    
    @Autowired
    private ${entityName}Repository repository;

    public List<${entityName}> findAll() {
        return repository.findAll();
    }

    public ${entityName} findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ${entityName} save(${entityName} entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
