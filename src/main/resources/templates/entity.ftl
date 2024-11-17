package com.lizatechnology.generated.entities;

<#if imports?? && (imports?size > 0)>
<#list imports as import>
import ${import};
</#list>
</#if>

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${entityName} {
    <#list fields as field>
    <#if field.annotations??>
    <#list field.annotations as annotation>
    ${annotation}
    </#list>
    </#if>
    private ${field.type} ${field.name};
    </#list>
}
