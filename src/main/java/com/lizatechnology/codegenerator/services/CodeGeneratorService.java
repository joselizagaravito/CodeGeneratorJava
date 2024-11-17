package com.lizatechnology.codegenerator.services;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.lizatechnology.codegenerator.dto.EntitySpec;
import com.lizatechnology.codegenerator.entities.Field;
import com.lizatechnology.codegenerator.utils.DynamicImportResolver;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@Service
public class CodeGeneratorService {

    private static final String TEMPLATE_PATH = "src/main/resources/templates";

    private void generateFromTemplate(String templateName, Map<String, Object> data, String outputPath) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        Template template = cfg.getTemplate(templateName);

        StringWriter writer = new StringWriter();
        template.process(data, writer);

        Path path = Paths.get(outputPath);
        Files.createDirectories(path.getParent());
        Files.write(path, writer.toString().getBytes());
    }

    public void generateEntity(EntitySpec entitySpec) throws IOException, TemplateException {
        String filePath = "src/main/java/com/lizatechnology/generated/entities/" + entitySpec.getName() + ".java";
        Map<String, Object> data = new HashMap<>();
        data.put("entityName", entitySpec.getName());
        data.put("fields", entitySpec.getFields());

        // Generar importaciones dinámicas
        Set<String> imports = new HashSet<>();
        for (Field field : entitySpec.getFields()) {
            String fieldType = field.getType();

            // Resolver importaciones para tipos genéricos
            DynamicImportResolver.resolveImport(fieldType).ifPresent(imports::add);
        }
        data.put("imports", imports);

        generateFromTemplate("entity.ftl", data, filePath);
    }


    public void generateRepository(EntitySpec entitySpec) throws IOException, TemplateException {
        String filePath = "src/main/java/com/lizatechnology/generated/repositories/" + entitySpec.getName() + "Repository.java";
        Map<String, Object> data = new HashMap<>();
        data.put("entityName", entitySpec.getName());
        generateFromTemplate("repository.ftl", data, filePath);
    }

    public void generateService(EntitySpec entitySpec) throws IOException, TemplateException {
        String filePath = "src/main/java/com/lizatechnology/generated/services/" + entitySpec.getName() + "Service.java";
        Map<String, Object> data = new HashMap<>();
        data.put("entityName", entitySpec.getName());
        generateFromTemplate("service.ftl", data, filePath);
    }

    public void generateController(EntitySpec entitySpec) throws IOException, TemplateException {
        String filePath = "src/main/java/com/lizatechnology/generated/controllers/" + entitySpec.getName() + "Controller.java";
        Map<String, Object> data = new HashMap<>();
        data.put("entityName", entitySpec.getName());
        generateFromTemplate("controller.ftl", data, filePath);
    }
}
