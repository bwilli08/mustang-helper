package com.cpe.musty.utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
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

    public static JSONObject readFromUrlString(String url) {
        try (InputStream is = new URL(url).openConnection().getInputStream()) {
            return readFromInputStream(is, str -> new JSONObject(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray readFromURLConnection(URLConnection connection) {
        try (InputStream is = connection.getInputStream()) {
            return readFromInputStream(is, str -> new JSONArray(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject readFromFile(String filename) {
        try (FileInputStream fr = new FileInputStream(filename)) {
            return readFromInputStream(fr, str -> new JSONObject(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T readFromInputStream(InputStream is, Function<String, T> func) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        T json = func.apply(jsonText);
        return json;
    }

    private static String readAll(Reader rd) {
        StringBuilder sb = new StringBuilder();

        try {
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
