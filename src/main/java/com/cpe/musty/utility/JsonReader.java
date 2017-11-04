package com.cpe.musty.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.function.Function;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonReader {

    public static JSONObject readJson(String url) {
        try {
            return readFromConnection(new URL(url).openConnection(), str -> new JSONObject(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray readArray(URLConnection connection) {
        return readFromConnection(connection, str -> new JSONArray(str));
    }

    private static <T> T readFromConnection(URLConnection connection, Function<String, T> func) {
        try (InputStream is = connection.getInputStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            T json = func.apply(jsonText);
            return json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
        } catch (IOException e) {

        }
        return sb.toString();
    }
}
