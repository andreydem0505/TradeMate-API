package com.herokuapp.trademateapi.demo;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Hash {
    private static final String SALT = "faseav74";

    public static String hashPassword(String password) {
        password += SALT;
        return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodePassword(String hash) {
        String password = new String(Base64.getDecoder().decode(hash), StandardCharsets.UTF_8);
        return password.substring(0, password.length()-SALT.length());
    }
}
