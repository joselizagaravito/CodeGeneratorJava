**🛠️ Code Generator Service**

**📖 Descripción**

El **Code Generator Service** es una herramienta basada en **Spring Boot** que permite generar automáticamente clases de **entidades**, **repositorios**, **servicios** y **controladores** en Java. Está diseñado para agilizar el desarrollo de aplicaciones y mantener consistencia en proyectos basados en el **Spring Framework**.

Este servicio utiliza **FreeMarker** para plantillas de código y maneja relaciones como @OneToMany y @ManyToOne. También incluye resolución dinámica de importaciones, optimizando el flujo de trabajo del desarrollador.

-----
**🚀 Características Principales**

- **Generación de Código Automático:**
  - **Entidades** con soporte para anotaciones JPA (@Entity, @Id, @Column).
  - **Repositorios** extendiendo JpaRepository.
  - **Servicios** CRUD preconfigurados.
  - **Controladores REST** listos para usar.
- **Resolución de Importaciones:**
  - Importa automáticamente tipos desde librerías estándar como:
    - java.util
    - java.math
    - java.time
- **Configuración Personalizable:**
  - Usa plantillas FreeMarker (.ftl) para definir estructuras de código adaptables.
-----
**📋 Requisitos**

- **Java 17** o superior.
- **Spring Boot 2.7+**.
- Dependencias:
  - **FreeMarker** para plantillas.
  - **Lombok** para reducir el código repetitivo.
- Sistema de construcción: **Maven**.
-----
**🏗️ Estructura del Proyecto**

plaintext

Copiar código

src/

├── main/

│   ├── java/

│   │   ├── com.lizatechnology.codegenerator/

│   │   │   ├── controller/          # Controlador REST principal.

│   │   │   ├── dto/                 # DTOs para solicitudes de generación.

│   │   │   ├── entities/            # Representación de campos para las entidades.

│   │   │   ├── services/            # Servicio para la generación de código.

│   │   │   └── utils/               # Resolución dinámica de importaciones.

│   ├── resources/

│   │   ├── templates/               # Plantillas FreeMarker.

│   │   └── type\_imports.txt         # Persistencia de importaciones dinámicas.

└── test/                            # Pruebas unitarias e integración.

-----
**🛠️ Configuración del Entorno**

1. **Clona este repositorio:**

   bash

   Copiar código

   git clone https://github.com/tu-usuario/code-generator-service.git

   cd code-generator-service

1. **Instala las dependencias:** Asegúrate de tener configurado **Maven** y **Java 17** o superior.
1. **Ejecuta la aplicación:**

   bash

   Copiar código

   mvn spring-boot:run

1. **Prueba el servicio:** Usa un cliente como **Postman** o curl para enviar solicitudes al endpoint /api/code-generator/generate.
-----
**📌 Ejemplo de Solicitud**

**Endpoint:**

bash

Copiar código

POST /api/code-generator/generate

**Cuerpo de la solicitud:**

json

Copiar código

{

`  `"entities": [

`    `{

`      `"name": "Category",

`      `"fields": [

`        `{

`          `"name": "id",

`          `"type": "Long",

`          `"annotations": [

`            `"@Id",

`            `"@GeneratedValue(strategy = GenerationType.IDENTITY)"

`          `]

`        `},

`        `{

`          `"name": "name",

`          `"type": "String",

`          `"annotations": [

`            `"@Column(nullable = false, unique = true)"

`          `]

`        `},

`        `{

`          `"name": "products",

`          `"type": "List<Product>",

`          `"annotations": [

`            `"@OneToMany(mappedBy = \"category\", cascade = CascadeType.ALL, orphanRemoval = true)"

`          `]

`        `}

`      `]

`    `}

`  `]

}

-----
**💻 Código Generado**

**Clase Category:**

java

Copiar código

package com.lizatechnology.generated.entities;

import java.util.List;

import jakarta.persistence.\*;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Data

@NoArgsConstructor

@AllArgsConstructor

public class Category {

`    `@Id

`    `@GeneratedValue(strategy = GenerationType.IDENTITY)

`    `private Long id;

`    `@Column(nullable = false, unique = true)

`    `private String name;

`    `@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)

`    `private List<Product> products;

}

-----
**🌟 Plantillas Personalizables**

Las plantillas están ubicadas en src/main/resources/templates.

**Plantilla de Entidad (entity.ftl):**

ftl

Copiar código

package com.lizatechnology.generated.entities;

<#if imports?? && (imports?size > 0)>

<#list imports as import>

import ${import};

</#list>

</#if>

import jakarta.persistence.\*;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Data

@NoArgsConstructor

@AllArgsConstructor

public class ${entityName} {

`    `<#list fields as field>

`    `<#if field.annotations??>

`    `<#list field.annotations as annotation>

`    `${annotation}

`    `</#list>

`    `</#if>

`    `private ${field.type} ${field.name};

`    `</#list>

}

-----
**🔄 Mejoras Futuras**

- Soporte para generación de **DTOs**.
- Validación de relaciones complejas.
- Configuración para nombres de paquetes personalizados.
- Soporte para relaciones bidireccionales.
-----
**🤝 Contribuciones**

¡Contribuye a este proyecto y ayuda a la comunidad de desarrolladores!

1. Haz un **fork** del repositorio.
1. Crea una nueva rama para tus cambios:

   bash

   Copiar código

   git checkout -b mi-feature

1. Sube tus cambios:

   bash

   Copiar código

   git add .

   git commit -m "Nueva funcionalidad"

   git push origin mi-feature

1. Abre un **pull request** y describe tus cambios.
-----
**📜 Licencia**

Este proyecto está licenciado bajo la [Licencia MIT](LICENSE).

-----
**📧 Contacto**

Para consultas, problemas o sugerencias, contáctanos en:

- **Email:** tu-email@dominio.com
- **GitHub:** <https://github.com/tu-usuario>

