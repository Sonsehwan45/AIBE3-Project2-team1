package com.ll;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTestRunner {
    public static String run(String input) {
        InputStream systemIn = System.in;
        PrintStream systemOut = System.out;

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        try {
            new App().run();
        } finally {
            System.setIn(systemIn);
            System.setOut(systemOut);
        }

        return out.toString();
    }
}