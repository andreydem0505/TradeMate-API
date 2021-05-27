package com.herokuapp.trademateapi.demo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Random;

public class DataEngine {
    public static final String emailIncorrect = "Email is incorrect";
    public static final String passwordUnreliable = "Password is unreliable";

    public static String generateAccessToken(Long id, String type) {
        String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        StringBuilder result = new StringBuilder();
        result.append(type);
        result.append("_");
        final Random random = new Random();
        for (int i = 0; i < 40; i++) {
            result.append(
                    alphabet.charAt(random.nextInt(alphabet.length()))
            );
        }
        result.append("_");
        result.append(id);
        return result.toString();
    }

    public static boolean requestHasHeader(HttpServletRequest request, String headerName) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            if (headers.nextElement().equals(headerName))
                return true;
        }
        return false;
    }

    public static LocalDateTime[] getRangeFromDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        LocalDateTime start = LocalDateTime.of(
                inputDate.getYear(),
                inputDate.getMonthValue(),
                inputDate.getDayOfMonth(), 0, 0);
        LocalDateTime stop = LocalDateTime.of(
                inputDate.getYear(),
                inputDate.getMonthValue(),
                inputDate.getDayOfMonth(), 23, 59);
        return new LocalDateTime[]{start, stop};
    }
}
