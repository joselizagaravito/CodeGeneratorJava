package com.lizatechnology.codegenerator.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private String name;
    private String type;
    
    @JsonSetter
    private List<String> annotations = new ArrayList<>(); 
   
}
