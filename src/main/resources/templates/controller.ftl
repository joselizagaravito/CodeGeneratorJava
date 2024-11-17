package com.lizatechnology.generated.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lizatechnology.generated.entities.${entityName};
import com.lizatechnology.generated.services.${entityName}Service;

@RestController
@RequestMapping("/api/${entityName?lower_case}")
public class ${entityName}Controller {

    @Autowired
    private ${entityName}Service service;

    @GetMapping
    public List<${entityName}> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ${entityName} findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ${entityName} save(@RequestBody ${entityName} entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
