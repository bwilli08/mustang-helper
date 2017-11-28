package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.helper.ResponseWrapper;

public class StopIntentHandler implements IntentHandler {

    @Override
    public SpeechletResponse handle(Intent intent) {
        return ResponseWrapper.newTellResponse("Goodbye.");
    }

}
