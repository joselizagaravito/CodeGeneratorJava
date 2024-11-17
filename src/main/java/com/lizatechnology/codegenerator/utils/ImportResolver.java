package com.lizatechnology.codegenerator.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @deprecated Esta clase ha sido reemplazada por {@link DynamicImportResolver}.
 * Use DynamicImportResolver para resolver importaciones dinámicamente con mayor flexibilidad.
 */
@Deprecated
public class ImportResolver {

    private static final Map<String, String> TYPE_TO_IMPORT_MAP = new HashMap<>();

    static {
        // Asocia tipos con sus paquetes
        TYPE_TO_IMPORT_MAP.put("BigDecimal", "java.math.BigDecimal");
        TYPE_TO_IMPORT_MAP.put("List", "java.util.List");
        TYPE_TO_IMPORT_MAP.put("Set", "java.util.Set");
        TYPE_TO_IMPORT_MAP.put("LocalDate", "java.time.LocalDate");
        TYPE_TO_IMPORT_MAP.put("LocalDateTime", "java.time.LocalDateTime");
    }
    public ImportResolver() {
        System.out.println("WARNING: ImportResolver está deprecado. Use DynamicImportResolver en su lugar.");
    }
    /**
     * Devuelve el paquete correspondiente al tipo proporcionado.
     * 
     * @param type El nombre del tipo (por ejemplo, "BigDecimal").
     * @return Un Optional con el paquete si existe.
     */
    public static Optional<String> resolveImport(String type) {
    	System.out.println("WARNING: ImportResolver está deprecado. Use DynamicImportResolver#resolveImport.");
        return Optional.ofNullable(TYPE_TO_IMPORT_MAP.get(type));
    }
}
