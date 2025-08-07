package com.ll.global.rq;

public class Rq {
    private final String[] parts;

    public Rq(String command) {
        this.parts = command.trim().split(" ");
    }

    public String getAction() {
        return parts.length > 0 ? parts[0] : "";
    }

    public String getParam(int index) {
        if (parts.length <= index) return null;
        return parts[index];
    }

    public int getIntParam(int index, int defaultValue) {
        try {
            return Integer.parseInt(getParam(index));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}