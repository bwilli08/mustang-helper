package com.cpe.musty.intent.helper;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;

public class ResponseWrapper {

    /**
     * Wrapper for creating the Ask response. The OutputSpeech and
     * {@link Reprompt} objects are created from the input strings.
     *
     * @param stringOutput
     *            the output to be spoken
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is
     *            misunderstood.
     * @return SpeechletResponse the speechlet response
     */
    public static SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(stringOutput);

        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
    }
    
    /**
     * Wrapper for creating the Ask response. The OutputSpeech and
     * {@link Reprompt} objects are created from the input strings.
     *
     * @param stringOutput
     *            the output to be spoken
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is
     *            misunderstood.
     * @return SpeechletResponse the speechlet response
     */
    public static SpeechletResponse newTellResponse(String stringOutput) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(stringOutput);

        return SpeechletResponse.newTellResponse(outputSpeech);
    }

}
