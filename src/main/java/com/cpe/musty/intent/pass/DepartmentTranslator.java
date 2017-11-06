package com.cpe.musty.intent.pass;

import java.util.Map;
import java.util.Optional;

import com.cpe.musty.utility.JsonReader;

import lombok.NonNull;

public class DepartmentTranslator {

    private static final String FILE_NAME = "departments.txt";

    @NonNull
    private final Map<String, Object> classMap;

    public DepartmentTranslator() {
        this.classMap = JsonReader.readFromFile(FILE_NAME).toMap();
    }

    public Optional<Integer> fromShortcode(final String departmentShortcode) {
        if (classMap.containsKey(departmentShortcode)) {
            return Optional.of(Integer.valueOf(String.valueOf(classMap.get(departmentShortcode))));
        }
        return Optional.empty();
    }

    public Optional<String> fromId(final Integer departmentId) {
        return classMap.entrySet().stream().filter(e -> e.getValue().equals(departmentId)).map(e -> e.getKey())
                .findFirst();
    }

}
