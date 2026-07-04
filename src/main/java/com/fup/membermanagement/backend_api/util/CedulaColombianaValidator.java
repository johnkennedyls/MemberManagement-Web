package com.fup.membermanagement.backend_api.util;

import lombok.experimental.UtilityClass;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class CedulaColombianaValidator {

    private static final int MIN_LENGTH = 7;
    private static final int MAX_LENGTH = 10;
    private static final List<String> EXCLUDED_PREFIXES = Arrays.asList("000", "999");

    public static boolean isValid(String cedula) {
        if (cedula == null || cedula.isBlank()) return false;

        String cleaned = cedula.replaceAll("[^\\d]", "");

        if (cleaned.length() < MIN_LENGTH || cleaned.length() > MAX_LENGTH) return false;
        if (EXCLUDED_PREFIXES.stream().anyMatch(cleaned::startsWith)) return false;

        return true;
    }

    public static String normalize(String cedula) {
        if (cedula == null) return null;
        return cedula.replaceAll("[^\\d]", "");
    }
}