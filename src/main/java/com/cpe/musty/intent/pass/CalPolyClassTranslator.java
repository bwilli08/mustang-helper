package com.cpe.musty.intent.pass;

import java.util.Map;

import org.json.JSONObject;

public class CalPolyClassTranslator {
    private static final String SUBJECT_KEY = "subject";
    private static final String CATALOG_NUMBER_KEY = "catalogNumber";
    private static final String UNITS_KEY = "maxUnits";

    public static CalPolyClass fromJson(JSONObject obj) {
        Map<String, Object> map = obj.toMap();

        return CalPolyClass.builder().departmentName(String.valueOf(map.get(SUBJECT_KEY)))
                .catalogNumber(Integer.valueOf(String.valueOf(map.get(CATALOG_NUMBER_KEY))))
                .units(Integer.valueOf(String.valueOf(map.get(UNITS_KEY)))).build();
    }
}
