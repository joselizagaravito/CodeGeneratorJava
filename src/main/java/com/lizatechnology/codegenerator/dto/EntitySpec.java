package com.lizatechnology.codegenerator.dto;

import java.util.List;

import com.lizatechnology.codegenerator.entities.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntitySpec {

    private String name;
    private List<Field> fields;

}
