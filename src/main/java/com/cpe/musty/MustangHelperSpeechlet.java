package com.cpe.musty;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.CancelIntentHandler;
import com.cpe.musty.intent.HelpIntentHandler;
import com.cpe.musty.intent.IntentHandler;
import com.cpe.musty.intent.StopIntentHandler;
import com.cpe.musty.intent.availability.ComputerAvailabilityIntentHandler;
import com.cpe.musty.intent.availability.FloorChecker;
import com.cpe.musty.intent.availability.FloorSynonyms;
import com.cpe.musty.intent.helper.AskResponseWrapper;
import com.cpe.musty.intent.pass.CheckPASSIntentHandler;
import com.google.common.collect.ImmutableMap;

/**
 * This is a Speechlet for an Alexa Skill called Mustang Helper. With it, Cal
 * Poly students are able to reserve library books, check PASS classes and
 * teachers, and see how busy the library is.
 */
public class MustangHelperSpeechlet implements Speechlet {

    /*
     * This should be the map of Intent Names to Intent Classes. See the
     * RecipeIntent class for a good example of how to handle an intent, grab a
     * value from it, etc.
     */
    // @formatter:off
    private static final Map<String, IntentHandler> INTENT_HANDLERS = ImmutableMap.of(
            "CheckPASSIntent", new CheckPASSIntentHandler(),
            "ComputerAvailability", new ComputerAvailabilityIntentHandler(new FloorChecker(), new FloorSynonyms()),
            "AMAZON.HelpIntent", HelpIntentHandler.getInstance(),
            "AMAZON.StopIntent", new StopIntentHandler(),
            "AMAZON.CancelIntent", new CancelIntentHandler());
    // @formatter:on

    private static final Logger log = LoggerFactory.getLogger(MustangHelperSpeechlet.class);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        String speechOutput = "Welcome to the Mustang Helper. You can reserve a book, check how busy the library "
                + "is, or ask about PASS classes and teachers. How can I help you?";

        String repromptText = "For instructions on what you can say, please say help me.";

        // Prompting the user for input
        return AskResponseWrapper.newAskResponse(speechOutput, repromptText);
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if (INTENT_HANDLERS.containsKey(intentName)) {
            try {
                return INTENT_HANDLERS.get(intentName).handle(intent);
            } catch (Exception e) {
                throw new SpeechletException(e);
            }
        }

        throw new SpeechletException("Invalid Intent");
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        // any cleanup logic goes here
    }

}
