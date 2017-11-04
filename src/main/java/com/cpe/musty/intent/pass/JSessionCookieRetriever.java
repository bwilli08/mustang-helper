package com.cpe.musty.intent.pass;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.common.base.Preconditions;

public class JSessionCookieRetriever {

    private static final String COOKIE = getJSessionCookie();

    public static String getCookie() {
        return COOKIE;
    }

    private static final String PASS_ENDPOINT = "https://pass.calpoly.edu/main.html";

    private static String getJSessionCookie() {
        return searchForCookie(PASS_ENDPOINT, 0);
    }

    private static String searchForCookie(final String endpoint, final int retry) {
        Preconditions.checkArgument(retry < 5);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            int resCode = conn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                String location = conn.getHeaderField("Location");
                if (location.startsWith("/")) {
                    location = url.getProtocol() + "://" + url.getHost() + location;
                }

                String setCookie = conn.getHeaderField("Set-Cookie");
                if (setCookie != null) {
                    return setCookie;
                }

                return searchForCookie(location, retry + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Can't find jsession id");
    }

}
