package com.ll;

public class Rq {
    private final String action;

    public Rq(String command) {
        String[] commandBits = command.split("\\?", 2);
        action = commandBits[0].trim();
    }

    public String getAction() {
        return action;
    }
}
