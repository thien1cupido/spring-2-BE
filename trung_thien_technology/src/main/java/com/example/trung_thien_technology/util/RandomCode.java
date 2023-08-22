package com.example.trung_thien_technology.util;

import java.util.List;

public class RandomCode {
    public static Integer randomBookCode(List<Integer>codes) {
        Integer code;
        do {
            code = (int) ((Math.random() * (999999 - 100000)) + 100000);
            if (codes.stream().noneMatch(code::equals)) {
                break;
            }
        } while (true);
        return code;
    }
}