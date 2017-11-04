package com.cpe.musty.intent.pass;

import lombok.NonNull;
import lombok.Value;

@Value
public class CalPolyClass {
    @NonNull
    private final String departmentName;
    @NonNull
    private final Integer catalogNumber;
    @NonNull
    private final Integer units;
}
