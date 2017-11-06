package com.cpe.musty.intent.pass;

import java.util.Map;
import java.util.Optional;

import com.cpe.musty.utility.JsonReader;

public class DepartmentTranslator {

    private static final String FILE_NAME = "departments.txt";

    private static final Map<String, Object> CLASS_MAP = JsonReader.readFromFile(FILE_NAME).toMap();

    public static Optional<Integer> fromShortcode(final String departmentShortcode) {
        if (CLASS_MAP.containsKey(departmentShortcode)) {
            return Optional.of(Integer.valueOf(String.valueOf(CLASS_MAP.get(departmentShortcode))));
        }
        return Optional.empty();
    }

    public static Optional<String> fromId(final Integer departmentId) {
        return CLASS_MAP.entrySet().stream().filter(e -> e.getValue().equals(departmentId)).map(e -> e.getKey())
                .findFirst();
    }

}
