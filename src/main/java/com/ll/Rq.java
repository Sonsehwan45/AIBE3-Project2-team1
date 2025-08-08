package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {
        String[] parts = cmd.trim().split(" ", 2);
        actionName = parts.length > 0 ? parts[0] : "";
        
        paramsMap = new HashMap<>();
        
        // 공백으로 구분된 명령어를 파싱
        if (parts.length >= 2) {
            // search 명령어인 경우 나머지를 모두 keyword로
            if (actionName.equals("search")) {
                paramsMap.put("keyword", parts[1]);
            } else if (actionName.equals("list")) {
                // list 명령어인 경우 정렬 옵션
                String sortOption = parts[1];
                if (sortOption.startsWith("--")) {
                    paramsMap.put("sort", sortOption.substring(2));
                } else {
                    paramsMap.put("sort", sortOption);
                }
            } else {
                // detail, update, delete 등 id를 사용하는 명령어
                String[] idParts = parts[1].split(" ");
                if (idParts.length > 0) {
                    paramsMap.put("id", idParts[0]);
                }
            }
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        } else {
            return defaultValue;
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if (value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean hasParam(String paramName) {
        return paramsMap.containsKey(paramName) && !paramsMap.get(paramName).isEmpty();
    }

    public boolean hasValidId() {
        return hasParam("id") && getParamAsInt("id", -1) > 0;
    }
}