package com.cpe.musty.intent.pass;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cpe.musty.utility.JsonReader;

public class PASSClassRetriever {

    private static final String SESSION_ID = JSessionCookieRetriever.getCookie();
    private static final String ENDPOINT = "https://pass.calpoly.edu/searchByDept.json?deptId=";

    public static List<CalPolyClass> getClassesForDeptId(Integer deptId) {
        try {
            URL url = new URL(ENDPOINT + deptId.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Cookie", SESSION_ID);

            JSONArray array = JsonReader.readFromURLConnection(conn);

            return array.toList().stream().map(JSONObject::new).map(CalPolyClassTranslator::fromJson)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
