package com.ll.standard.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}