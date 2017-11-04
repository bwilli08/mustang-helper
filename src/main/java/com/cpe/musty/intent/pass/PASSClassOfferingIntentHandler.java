package com.cpe.musty.intent.pass;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.cpe.musty.intent.IntentHandler;

/*
 * TODO: Finish this class. There's functionality to find the corresponding department id and send a request to
 * the PASS endpoint, but we still need to add functionality to filter that response and check for a specific class.
 * 
 * TODO: We'll have to do some additional web scraping in order to return day/time and professor information.
 */
public class PASSClassOfferingIntentHandler implements IntentHandler {

    private static final String SESSION_ID = JSessionCookieRetriever.getCookie();
    private static final String ENDPOINT = "https://pass.calpoly.edu/searchByDept.json?deptId=";

    @Override
    public SpeechletResponse handle(Intent intent) {

        // Parse intent to retrieve the department name they're searching for.
        // Convert any synonyms
        // Convert from department name to departmentId

        // URL url = new URL(ENDPOINT + departmentId.toString());
        // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // conn.setInstanceFollowRedirects(false);
        // conn.setRequestProperty("Cookie", SESSION_ID);

        return null;
    }
}
