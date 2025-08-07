package com.ll.domain.system.controller;

public class SystemController {
    public void exit() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }
    public boolean shouldExit(String command) {
        return "exit".equalsIgnoreCase(command.trim());
    }
}
