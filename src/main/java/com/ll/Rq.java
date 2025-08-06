package com.ll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    //private final List<Integer> idList = new ArrayList<>();
    private final int id;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split(" ", 2);
        actionName = cmdBits[0];
        String queryString = cmdBits.length > 1 ? cmdBits[1].trim() : "";
        if (!queryString.isEmpty()){id = Integer.parseInt(queryString);}
        else{id = -1;}


    }
    public String getActionName() { return actionName;}


    public int getParamsAsInt(String paramName, int defaultValue){
        if(id<0){
            return defaultValue;
        }

        try{
            return id;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
