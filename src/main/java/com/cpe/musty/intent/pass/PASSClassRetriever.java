package com.cpe.musty.intent.pass;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cpe.musty.utility.JsonReader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PASSClassRetriever {

    private static final String ENDPOINT = "https://pass.calpoly.edu/searchByDept.json?deptId=";

    public List<CalPolyClass> getClassesForDeptId(Integer deptId) {
        try {
            String sessionId = JSessionCookieRetriever.getCookie();

            URL url = new URL(ENDPOINT + deptId.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Cookie", sessionId);

            JSONArray array = JsonReader.readFromURLConnection(conn);

            // Cast to HashMap to ensure the correct JSONObject constructor is used
            return array.toList().stream().map(HashMap.class::cast).map(JSONObject::new)
                    .map(CalPolyClassTranslator::fromJson).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving information from PASS.", e);
        }
    }

}
