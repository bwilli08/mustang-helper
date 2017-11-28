package com.cpe.musty.intent.pass;

import java.util.Optional;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.IntentHandler;
import com.cpe.musty.intent.helper.ResponseWrapper;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CheckPASSIntentHandler implements IntentHandler {

    // Key to get the course number from the intent
    private static final String COURSE_SLOT = "Course";

    @NonNull
    private final DepartmentTranslator departmentTranslator;
    @NonNull
    private final PASSClassRetriever passClassRetriever;

    @Override
    public SpeechletResponse handle(Intent intent) {
        Slot courseSlot = intent.getSlot(COURSE_SLOT);

        if (isValid(courseSlot)) {
            String[] courseString = courseSlot.getValue().split(" ");
            String departmentName = courseString[0].toUpperCase();
            Integer courseNumber = Integer.valueOf(courseString[1]);

            Integer departmentId = departmentTranslator.fromShortcode(departmentName).orElseThrow(
                    () -> new RuntimeException(String.format("%s is an invalid department name.", departmentName)));

            Optional<CalPolyClass> calPolyClass = passClassRetriever.getClassesForDeptId(departmentId).stream()
                    .filter(cls -> cls.getDepartmentName().equals(departmentName)
                            && cls.getCatalogNumber().equals(courseNumber))
                    .findFirst();

            String offered = calPolyClass.isPresent() ? "" : "not";

            return ResponseWrapper.newTellResponse(
                    String.format("%s %s is %s offered next quarter.", departmentName, courseNumber, offered));
        } else {
            String output = "I'm sorry, I didn't understand what course you're asking about. "
                    + "Please specify the department name and course number.";
            String reprompt = "What else can I do?";

            return ResponseWrapper.newAskResponse(output, reprompt);
        }
    }

    private boolean isValid(Slot slot) {
        return slot != null && slot.getValue() != null;
    }

}
