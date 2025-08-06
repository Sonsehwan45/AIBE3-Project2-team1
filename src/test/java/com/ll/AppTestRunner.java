package com.ll;

import com.ll.App;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTestRunner {
    public static String run(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        new App().run();

        String result = out.toString();

        System.setIn(System.in);
        System.setOut(System.out);

        return result;
    }
}