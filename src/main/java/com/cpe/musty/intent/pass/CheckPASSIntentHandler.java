package com.cpe.musty.intent.pass;

import java.util.Optional;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.HelpIntentHandler;
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
        try {
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

                String offered = calPolyClass.isPresent() ? "is" : "is not";

                return ResponseWrapper.newTellResponse(
                        String.format("%s %s %s offered next quarter.", departmentName, courseNumber, offered));
            } else {
                String output = "I'm sorry, I didn't understand what course you're asking about.";
                String reprompt = "Please specify the department name and course number.";

                return ResponseWrapper.newAskResponse(output, reprompt);
            }
        } catch (Exception e) {
            HelpIntentHandler helpHandler = HelpIntentHandler.getInstance();
            helpHandler.setSpeechOutput(e.getMessage());
            return helpHandler.handle(intent);
        }
    }

    private boolean isValid(Slot slot) {
        return slot != null && slot.getValue() != null;
    }

}
