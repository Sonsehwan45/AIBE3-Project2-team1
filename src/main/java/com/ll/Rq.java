package com.ll;

public class Rq {
    private String action;
    private int id;

    public Rq(String cmd) {
        String[] parts = cmd.split(" ");
        this.action = parts[0];
        if (parts.length > 1) {
            try {
                this.id = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                this.id = -1;
            }
        }
    }

    public String getAction() {
        return action;
    }

    public int getId() {
        return id;
    }
}
