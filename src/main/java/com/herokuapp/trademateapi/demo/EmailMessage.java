package com.herokuapp.trademateapi.demo;

public class EmailMessage {
    public final String to;
    public final String subject;
    public final String text;

    public EmailMessage(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
}
