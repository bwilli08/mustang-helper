package com.cpe.musty.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.helper.AskResponseWrapper;

import lombok.Setter;

public class HelpIntentHandler implements IntentHandler {

    private HelpIntentHandler() {
    }

    private static final HelpIntentHandler INSTANCE = new HelpIntentHandler();

    public static HelpIntentHandler getInstance() {
        return INSTANCE;
    }

    private static final String DEFAULT_SPEECH = "There was an error with your request.";

    @Setter
    private String speechOutput = DEFAULT_SPEECH;

    @Override
    public SpeechletResponse handle(Intent intent) {
        String repromptText = "You can check if a class is offered next quarter or see how many computers are "
                + "available in the library, or, you can say exit... Now, what can I help you with?";

        SpeechletResponse response = AskResponseWrapper.newAskResponse(speechOutput, repromptText);

        setSpeechOutput(DEFAULT_SPEECH);

        return response;
    }

}
