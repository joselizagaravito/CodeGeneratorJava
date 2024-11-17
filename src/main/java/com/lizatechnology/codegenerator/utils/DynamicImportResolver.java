package com.lizatechnology.codegenerator.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DynamicImportResolver {

    private static final String IMPORTS_FILE = "src/main/resources/type_imports.txt";
    private static final Map<String, String> TYPE_TO_IMPORT_MAP = new HashMap<>();
    private static final Set<String> KNOWN_PACKAGES = Set.of("java.math", "java.util", "java.time", "jakarta.persistence");

    static {
        // Cargar el archivo de importaciones al iniciar la aplicación
        loadImportsFromFile();
    }

    /**
     * Resuelve la importación de un tipo dado.
     * Si el tipo no existe, usa reflexión para determinarlo y lo guarda en el archivo.
     *
     * @param type El nombre del tipo (por ejemplo, "BigDecimal").
     * @return Un Optional con la importación completa si existe.
     */
    public static Optional<String> resolveImport(String type) {
        // Manejar tipos genéricos como List<Product>
        if (type.contains("<")) {
            String containerType = type.substring(0, type.indexOf("<")); // Ejemplo: "List"
            String genericType = type.substring(type.indexOf("<") + 1, type.indexOf(">")); // Ejemplo: "Product"

            // Resolver el tipo contenedor
            resolveImport(containerType).ifPresent(fullType -> TYPE_TO_IMPORT_MAP.put(containerType, fullType));

            // Resolver el tipo genérico
            resolveImport(genericType).ifPresent(fullType -> TYPE_TO_IMPORT_MAP.put(genericType, fullType));

            return Optional.ofNullable(TYPE_TO_IMPORT_MAP.get(containerType));
        }

        // Buscar en el mapa para tipos no genéricos
        if (TYPE_TO_IMPORT_MAP.containsKey(type)) {
            return Optional.of(TYPE_TO_IMPORT_MAP.get(type));
        }

        // Usar reflexión si no está en el mapa
        for (String knownPackage : KNOWN_PACKAGES) {
            try {
                Class.forName(knownPackage + "." + type);
                String fullType = knownPackage + "." + type;

                // Guardar en memoria y archivo
                TYPE_TO_IMPORT_MAP.put(type, fullType);
                appendImportToFile(type, fullType);

                return Optional.of(fullType);
            } catch (ClassNotFoundException e) {
                // Ignorar si no está en este paquete
            }
        }

        return Optional.empty(); // Tipo no encontrado
    }


    /**
     * Carga las importaciones desde el archivo en el mapa.
     */
    private static void loadImportsFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(IMPORTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    TYPE_TO_IMPORT_MAP.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar el archivo de importaciones: " + e.getMessage());
        }
    }

    /**
     * Agrega una nueva importación al archivo.
     *
     * @param type El nombre del tipo (por ejemplo, "BigDecimal").
     * @param fullType El paquete completo del tipo (por ejemplo, "java.math.BigDecimal").
     */
    private static void appendImportToFile(String type, String fullType) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(IMPORTS_FILE), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(type + "=" + fullType);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("No se pudo escribir en el archivo de importaciones: " + e.getMessage());
        }
    }
}
