package com.lizatechnology.codegenerator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizatechnology.codegenerator.dto.EntityRequest;
import com.lizatechnology.codegenerator.dto.EntitySpec;
import com.lizatechnology.codegenerator.services.CodeGeneratorService;

import freemarker.template.TemplateException;

@RestController
@RequestMapping("/api/code-generator")
public class CodeGeneratorController {

    @Autowired
    private CodeGeneratorService generatorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateCode(@RequestBody EntityRequest request) {
        try {
            for (EntitySpec entity : request.getEntities()) {
                generatorService.generateEntity(entity);
                generatorService.generateRepository(entity);
                generatorService.generateService(entity);
                generatorService.generateController(entity);
            }
            return ResponseEntity.ok("Código generado exitosamente.");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar código: " + e.getMessage());
        }
    }

}