package com.ll;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String action;
    private final Map<String, String> params;

    public Rq(String command) {
        String[] cmdBits = command.split("\\?", 2);
        action = cmdBits[0].trim();

        String queryString = cmdBits.length == 2  ? cmdBits[1].trim() : "";

        params = Arrays
                .stream(queryString.split("&"))
                .map(e -> e.split("=", 2))
                .filter(e -> e.length == 2 && !e[0].isBlank() && !e[1].isBlank())
                .collect(Collectors.toMap(e -> e[0].trim(), e -> e[1].trim()));
    }

    public String getAction() {
        return action;
    }

    public int getIntParam(String name, int defaultValue) {
        try {
            return Integer.parseInt(params.get(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
