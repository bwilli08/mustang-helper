package com.cpe.musty.intent.availability;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class FloorSynonyms {

    // These should match the fl_string values in the Alexa Interaction Model
    // @formatter:off
    private Map<Integer, List<String>> syns = ImmutableMap.of(
            1, ImmutableList.of("1", "first", "1st", "bottom", "main", "initial"),
            2, ImmutableList.of("2", "second", "2nd"),
            3, ImmutableList.of("3", "third", "3rd"),
            4, ImmutableList.of("4", "fourth", "4th"),
            5, ImmutableList.of("5", "fifth", "5th", "top"));
    // @formatter:on

    public Optional<Integer> findMatch(final String utterance) {
        return syns.entrySet().stream()
                .filter(entry -> entry.getValue().contains(utterance))
                .map(Entry::getKey)
                .findFirst();
    }
    
}
