package com.ll;

import java.util.Scanner;

public class AppContext {
    private static Scanner scanner;

    public static void init() {
        scanner = new Scanner(System.in);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}