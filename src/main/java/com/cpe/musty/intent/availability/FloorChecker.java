package com.cpe.musty.intent.availability;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.cpe.musty.utility.JsonReader;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FloorChecker {

    // @formatter:off
    private static final Map<Integer, String> FLOOR_ENDPOINTS= ImmutableMap.of(
            1, "http://lib.calpoly.edu/api/availability/1st",
            2, "http://lib.calpoly.edu/api/availability/2nd",
            3, "http://lib.calpoly.edu/api/availability/3rd",
            4, "http://lib.calpoly.edu/api/availability/4th",
            5, "http://lib.calpoly.edu/api/availability/5th");
    // @formatter:on

    private static final int AVAILABLE_STATUS = 0;
    private static final String STATUS_KEY = "status";

    public long findAllAvailableComputers() {
        return FLOOR_ENDPOINTS.keySet().stream().map(this::findAvailableComputers).reduce((x1,x2) -> x1 + x2).get();
    }

    public long findAvailableComputers(final Integer floorNumber) {
        if (FLOOR_ENDPOINTS.containsKey(floorNumber)) {
            final String endpoint = FLOOR_ENDPOINTS.get(floorNumber);
            final JSONObject floorJson = getJsonFromEndpoint(endpoint);
            final List<JSONObject> computers = convertToComputerList(floorJson);

            return computers.stream().filter(o -> o.getInt(STATUS_KEY) == AVAILABLE_STATUS).count();
        }

        throw new RuntimeException(String.format("%s is an invalid floor number.", floorNumber.toString()));
    }

    private JSONObject getJsonFromEndpoint(final String endpoint) {
        try {
            return JsonReader.readFromUrlString(endpoint);
        } catch (Exception e) {
            throw new RuntimeException("There was an error checking computer availability.", e);
        }
    }

    private List<JSONObject> convertToComputerList(final JSONObject floorMap) {
        return floorMap.toMap().values().stream().map(o -> new JSONObject((Map) o)).filter(o -> o.has(STATUS_KEY))
                .collect(ImmutableList.toImmutableList());
    }
}
