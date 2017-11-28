package com.cpe.musty.intent.availability;

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
public class ComputerAvailabilityIntentHandler implements IntentHandler {

    private static final String FL_NUMBER = "fl_number";
    private static final String FL_STRING = "fl_string";

    @NonNull
    private final FloorChecker floorChecker;
    @NonNull
    private final FloorSynonyms floorSynonyms;

    @Override
    public SpeechletResponse handle(Intent intent) {
        Slot fl_number = intent.getSlot(FL_NUMBER);
        Slot fl_string = intent.getSlot(FL_STRING);

        Optional<Integer> floor = getFloorNumber(fl_number, fl_string);

        // For now, just ignore the floor number slot and check the first floor
        try {
            if (floor.isPresent()) {
                long availableComputers = floorChecker.findAvailableComputers(floor.get());

                return ResponseWrapper.newTellResponse(String.format("There are %s available computers on that floor.",
                        Long.toString(availableComputers)));
            } else {
                long availableComputers = floorChecker.findAllAvailableComputers();

                return ResponseWrapper.newTellResponse(String.format("There are %s available computers in the library.",
                        Long.toString(availableComputers)));
            }
        } catch (Exception e) {
            HelpIntentHandler helpHandler = HelpIntentHandler.getInstance();
            helpHandler.setSpeechOutput(e.getMessage());
            return helpHandler.handle(intent);
        }
    }

    private Optional<Integer> getFloorNumber(final Slot fl_num, final Slot fl_str) {
        if (fl_num != null && fl_num.getValue() != null) {
            return Optional.of(Integer.valueOf(fl_num.getValue()));
        } else if (fl_str != null && fl_str.getValue() != null) {
            Optional<Integer> floor = floorSynonyms.findMatch(fl_str.getValue());

            return Optional.of(floor.orElseThrow(
                    () -> new RuntimeException(String.format("%s is an invalid floor.", fl_str.getValue()))));
        }

        return Optional.empty();
    }
}
