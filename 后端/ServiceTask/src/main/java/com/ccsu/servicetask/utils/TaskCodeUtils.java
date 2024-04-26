package com.ccsu.servicetask.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskCodeUtils {
    public static void main(String[] args) {
        System.out.println(createCode());
    }

    public static String createCode() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd-HHmmssSSS");
        return "T-" + time.format(formatter);
    }
}
